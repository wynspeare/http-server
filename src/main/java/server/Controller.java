package server;

import HTTPcomponents.Methods;
import server.handlers.IHandler;
import server.request.Request;

import java.util.HashMap;
import java.util.List;

public class Controller implements IController {

  public Response handle(Request request, HashMap<String, List<HashMap<Methods, IHandler>>> routes) {
    Response response = new Response();
    String requestPath = request.getRequestPath();
    if (isValidURI(requestPath, routes)) {
      List<HashMap<Methods, IHandler>> allowedMethods = routes.get(requestPath);
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

  public boolean isValidURI(String uri, HashMap<String, List<HashMap<Methods, IHandler>>> routes) {
    return routes.containsKey(uri);
  }
}
