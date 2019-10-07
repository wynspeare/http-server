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
  public IController controller;

  public Router(ILogger serverLogger, IController controller) {
    routes = new HashMap<>();
    this.serverLogger = serverLogger;
    this.controller = controller;
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
      return controller.handle(request, routes);
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
}
