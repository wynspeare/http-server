package server;

import HTTPcomponents.Methods;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import server.handlers.DefaultHandler;
import server.handlers.IHandler;
import server.handlers.RedirectHandler;
import server.logger.LoggerSpy;
import server.request.Request;
import server.utils.InvalidRequestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class RouterTest {
  @Test
  public void routerHasAnEmptyHashMapOfRoutes() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);

    assertTrue(router.routes.isEmpty());
  }

  @Test
  public void routerCanCheckIfIncomingMethodIsValid() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);

    try {
      assertTrue(router.isValidMethod("GET"));
    } catch (InvalidRequestException e) {
      e.printStackTrace();
    }
  }

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void routerCanCheckIfIncomingMethodIsNotValidUsingExceptionRule() throws Exception {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    exceptionRule.expect(InvalidRequestException.class);
    exceptionRule.expectMessage("Request Method Not Found");
    router.isValidMethod("Unknown METHOD");
  }

  @Test
  public void routerCanAddURIToAHashMapOfRoutes() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/simple_get", new DefaultHandler());

    assertTrue(router.routes.containsKey("/simple_get"));
  }

  @Test
  public void routerCannotAddAnRouteWithInvalidMethodToRoutes() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("Unknown Method", "/simple_get", new DefaultHandler());

    assertTrue(testLogger.getLoggedMessage().contains("\"Unknown Method\" - Request Method Not Found"));
  }

  @Test
  public void routerCanChecksTheURIinARequest() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/random_path", new DefaultHandler());
    Request request = new Request("GET /random_path HTTP/1.1");

    assertTrue(router.isValidURI(request.getRequestPath()));
  }

  @Test
  public void routerCanChecksTheURIinARequestInvalid() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/random_path", new DefaultHandler());
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertFalse(router.isValidURI(request.getRequestPath()));
  }

  @Test
  public void routerCanGetAllowedMethodWithManuallyBuiltAllowedRoutes() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    HashMap<Methods, IHandler> methodHandler = new HashMap<>();

    IHandler getHandler = new DefaultHandler();
    IHandler redirectHandler = new RedirectHandler("http://127.0.0.1:5000/simple_get");

    methodHandler.put(Methods.GET, getHandler);
    List<HashMap<Methods, IHandler>> allowedRoutes = new ArrayList();
    allowedRoutes.add(methodHandler);
    HashMap<Methods, IHandler> methodHandler2 = new HashMap<>();
    methodHandler2.put(Methods.POST, redirectHandler);
    allowedRoutes.add(methodHandler2);

    Request request = new Request("GET /simple_get HTTP/1.1");

    assertTrue(router.getAllowedMethodHandler(allowedRoutes, request) instanceof IHandler);
  }

  @Test
  public void routerCanAddTwoMethodsForOneURI() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/simple_get", new DefaultHandler());
    router.addRoute("HEAD", "/simple_get", new DefaultHandler());

    assertTrue(router.routes.get("/simple_get") instanceof List);
    assertTrue(router.routes.get("/simple_get").get(0).get(Methods.GET) instanceof DefaultHandler);
    assertTrue(router.routes.get("/simple_get").get(1).get(Methods.HEAD) instanceof DefaultHandler);
  }

  @Test
  public void routerCanGetSingleSlashURIFromARequest() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/", new DefaultHandler());
    Request request = new Request("GET / HTTP/1.1");

    assertTrue(router.isValidURI(request.getRequestPath()));
  }
}