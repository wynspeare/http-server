package server;

import HTTPcomponents.Methods;
import HTTPcomponents.StatusCode;
import org.junit.Test;
import server.request.Handler;
import server.request.Request;

import static org.junit.Assert.*;

public class RouterTest {
  @Test
  public void routerHasAnEmptyHashMapOfRoutes() {
    Router router = new Router();

    assertTrue(router.routes.isEmpty());
  }

  @Test
  public void routerCanCheckIfIncomingMethodIsValid() {
    Router router = new Router();

    assertTrue(router.isValidMethod("GET"));
  }

  @Test
  public void routerCanCheckIfIncomingMethodIsInValid() {
    Router router = new Router();

    assertFalse(router.isValidMethod("BAD METHOD"));
  }

  @Test
  public void routerCanAddURIToAHashMapOfRoutes() {
    Router router = new Router();
    router.addRoute("GET", "/simple_get");

    assertTrue(router.routes.containsKey(Methods.GET));
  }

  @Test
  public void routerCanChecksTheURIinARequest() {
    Router router = new Router();
    router.addRoute("GET", "/random_path");

    Request request = new Request("GET /random_path HTTP/1.1");

    assertTrue(router.isValidURI(request.getRequestPath()));
  }

  @Test
  public void routerCanChecksTheURIinARequestInvalid() {
    Router router = new Router();
    router.addRoute("GET", "/random_path");

    Request request = new Request("GET /simple_get HTTP/1.1");

    assertFalse(router.isValidURI(request.getRequestPath()));
  }

}