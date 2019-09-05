package server;

import HTTPcomponents.Methods;
import org.junit.Test;
import server.request.Request;

import java.util.HashMap;
import java.util.Map;

import static HTTPcomponents.StatusLineComponents.VERSION;
import static org.junit.Assert.*;

public class RequestTest {
  @Test
  public void requestTakesInAStatusLineAndCanReturnTheHTTPMethod() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("GET", request.getRequestMethod());
  }

  @Test
  public void requestTakesInAStatusLineAndCanReturnTheRoute() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("/simple_get", request.getRequestPath());
  }

  @Test
  public void requestTakesInAStatusLineAndCanReturnTheVersion() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("HTTP/1.1", request.getRequestVersion());
  }

  @Test
  public void requestTakesInAStatusLineAndCanGrabTheVersionUsingConstantVariable() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals(VERSION, request.getRequestVersion());
  }

  @Test
  public void canMatchIncomingRequestToEnumHTTPMethod() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals(Methods.GET.toString(), request.getRequestMethod());
  }
}