package server;

import HTTPcomponents.Methods;
import server.handlers.IHandler;
import server.logger.ILogger;
import server.request.Request;
import server.utils.InvalidRequestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Router {
  public HashMap<String, List<HashMap<Methods, IHandler>>> routes;
  public ILogger serverLogger;

  public Router(ILogger serverLogger) {
    routes = new HashMap<>();
    this.serverLogger = serverLogger;
  }

  public void addRoute(String method, String uri, IHandler handler) {
    List<HashMap<Methods, IHandler>> addedRoutes = new ArrayList();

    try {
      if (isValidMethod(method)) {
        if (!routes.containsKey(uri)) {
          HashMap<Methods, IHandler> newRoute = new HashMap<Methods, IHandler>() {{
            put(getMethod(method), handler);
          }};
          addedRoutes.add(newRoute);
          routes.put(uri, addedRoutes);
        } else {
          HashMap<Methods, IHandler> newRoute = new HashMap<Methods, IHandler>() {{
            put(getMethod(method), handler);
          }};
          routes.get(uri).add(newRoute);
        }
      }
    } catch (InvalidRequestException e) {
      serverLogger.log(e.getMessage(), method);
    }
  }

  public Response handle(Request request) {
    Response response = new Response();
    if (isValidURI(request.getRequestPath())) {
      List<HashMap<Methods, IHandler>> allowedMethods = routes.get(request.getRequestPath());
      IHandler handler = getAllowedMethodHandler(allowedMethods, request);
      response = handler.buildResponse(request);
    }
    return response;
  }

  public IHandler getAllowedMethodHandler(List<HashMap<Methods, IHandler>> allowedMethods, Request request) {
    IHandler handler = null;
    for (HashMap<Methods, IHandler> element : allowedMethods) {
      if (element.containsKey(request.getMethod())) {
        handler = element.get(request.getMethod());
      }
    }
    return handler;
  }

  public Methods getMethod(String method) {
    return Methods.valueOf(method);
  }

  public boolean isValidMethod(String method) throws InvalidRequestException {
    try {
      Methods.valueOf(method);
      return true;
    } catch (Exception e) {
      throw new InvalidRequestException("Request Method Not Found", e);
    }
  }

  public boolean isValidURI(String uri) {
    return routes.containsKey(uri);
  }

}
