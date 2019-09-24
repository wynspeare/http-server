package server;

import HTTPcomponents.Methods;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import server.handlers.DefaultHandler;
import server.handlers.IHandler;
import server.handlers.RedirectHandler;
import server.request.Request;
import server.utils.InvalidRequestException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class RouterTest {
//  @Test
//  public void routerHasAnEmptyHashMapOfRoutes() {
//    Router router = new Router();
//
//    assertTrue(router.routes.isEmpty());
//  }
//
//  @Test
//  public void routerCanCheckIfIncomingMethodIsValid() {
//    Router router = new Router();
//
//    try {
//      assertTrue(router.isValidMethod("GET"));
//    } catch (InvalidRequestException e) {
//      e.printStackTrace();
//    };
//  }
//
//  @Rule
//  public ExpectedException exceptionRule = ExpectedException.none();
//
//  @Test
//  public void routerCanCheckIfIncomingMethodIsNotValidUsingExceptionRule() throws Exception {
//    Router router = new Router();
//    exceptionRule.expect(InvalidRequestException.class);
//    exceptionRule.expectMessage("Request Method Not Found");
//    router.isValidMethod("Unknown METHOD");
//  }
//
//  @Test
//  public void routerCanAddURIToAHashMapOfRoutes() {
//    Router router = new Router();
//    router.addRoute("GET", "/simple_get", new DefaultHandler());
//
//    assertTrue(router.routes.containsKey("/simple_get"));
//  }

  @Test
  public void routerCannotAddAnRouteWithInvalidMethodToRoutes() {
    ServerLogger testLogger = HTTPServer.createLogger("TestLogs");
    Router router = new Router(testLogger);
    router.addRoute("Unknown Method", "/simple_get", new DefaultHandler());

    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader("TestLogs/serverLogger.log.0"));
      String line1 = in.readLine();
      String line2 = in.readLine();
      System.out.println(line2);
      assertTrue(line2.contains("Request Method Not Found"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    File file = new File("TestLogs/serverLogger.log.0");
    file.delete();
  }

//
//  @Test
//  public void routerCanChecksTheURIinARequest() {
//    Router router = new Router();
//    router.addRoute("GET", "/random_path", new DefaultHandler());
//    Request request = new Request("GET /random_path HTTP/1.1");
//
//    assertTrue(router.isValidURI(request.getRequestPath()));
//  }
//
//  @Test
//  public void routerCanChecksTheURIinARequestInvalid() {
//    Router router = new Router();
//    router.addRoute("GET", "/random_path", new DefaultHandler());
//    Request request = new Request("GET /simple_get HTTP/1.1");
//
//    assertFalse(router.isValidURI(request.getRequestPath()));
//  }
//
//  @Test
//  public void routerCanGetAllowedMethodWithManuallyBuiltAllowedRoutes() {
//    Router router = new Router();
//    HashMap<Methods, IHandler> methodHandler = new HashMap<>();
//
//    IHandler getHandler = new DefaultHandler();
//    IHandler redirectHandler = new RedirectHandler();
//
//    methodHandler.put(Methods.GET, getHandler);
//    List<HashMap<Methods, IHandler>> allowedRoutes = new ArrayList();
//    allowedRoutes.add(methodHandler);
//    HashMap<Methods, IHandler> methodHandler2 = new HashMap<>();
//    methodHandler2.put(Methods.POST, redirectHandler);
//    allowedRoutes.add(methodHandler2);
//
//    Request request = new Request("GET /simple_get HTTP/1.1");
//
//    assertTrue(router.getAllowedMethodHandler(allowedRoutes, request) instanceof IHandler);
//  }
//
//  @Test
//  public void routerCanAddTwoMethodsForOneURI() {
//    Router router = new Router();
//    router.addRoute("GET", "/simple_get", new DefaultHandler());
//    router.addRoute("HEAD", "/simple_get", new DefaultHandler());
//
//    assertTrue(router.routes.get("/simple_get") instanceof List);
//    assertTrue(router.routes.get("/simple_get").get(0).get(Methods.GET) instanceof DefaultHandler);
//    assertTrue(router.routes.get("/simple_get").get(1).get(Methods.HEAD) instanceof DefaultHandler);
//  }
//
//  @Test
//  public void routerCanHandleARequest() {
//    Router router = new Router();
//    router.addRoute("GET", "/simple_get", new DefaultHandler());
//    router.addRoute("HEAD", "/simple_get", new DefaultHandler());
//
//    Request request = new Request("GET /simple_get HTTP/1.1");
//
//    assertEquals("HTTP/1.1 200 OK\r\n", router.handle(request).getStatusLine());
//  }

}