package server;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
  @Test
  public void requestTakesInAStatusLineAndCanReturnTheMethod() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("GET", request.getRequestMethod());
  }

  @Test
  public void requestTakesInAStatusLineAndCanReturnThePath() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("/simple_get", request.getRequestPath());
  }

  @Test
  public void requestTakesInAStatusLineAndCanReturnTheVersion() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("HTTP/1.1", request.getRequestVersion());
  }

  @Test
  public void requestTakesInAStatusLineAndCanReturnTheHeaders() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("HTTP/1.1", request.getRequestVersion());
  }

}