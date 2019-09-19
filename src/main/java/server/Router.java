package server;

import HTTPcomponents.Methods;

import java.util.HashMap;

public class Router {
  public HashMap<String, HashMap<Methods, IHandler>> routes;

  public Router() {
    routes = new HashMap<>();
  }

  public void addRoute(String method, String uri) {
    HashMap<Methods, IHandler> methodHandler = new HashMap<>();

    if (isValidMethod(method)) {
      methodHandler.put(getMethod(method), getHandler(uri));
      routes.put(uri, methodHandler);
    }
  }

  public IHandler getHandler(String uri) {
    IHandler handler;
    if (uri == "/redirect") {
        handler = new RedirectHandler();
      } else {
      handler = new GetHandler();
    }
    return handler;
  }

  public Methods getMethod(String method) {
    return Methods.valueOf(method);
  }

  public boolean isValidMethod(String method) {
    try {
      Methods.valueOf(method);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean isValidURI(String uri) {
    return routes.containsKey(uri);
  }

}
