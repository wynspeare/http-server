package server;

import HTTPcomponents.Methods;

import java.util.HashMap;

public class Router {
  public HashMap<Methods, String> routes;

  public Router() {
    routes = new HashMap<>();
  }

  public void addRoute(String method, String uri) {
    if (isValidMethod(method)) {
      routes.put(getMethod(method), uri);
    }
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
    return routes.containsValue(uri);
  }

}
