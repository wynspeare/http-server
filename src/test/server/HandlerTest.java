package server;

import org.junit.Test;
import server.request.Request;
import server.request.Handler;

import static org.junit.Assert.*;

public class HandlerTest {
  @Test
  public void handlerChecksIfRequestIsAGET() {
    Request request = new Request("GET /simple_get HTTP/1.1");
    Handler handler = new Handler(request);

    assertTrue(handler.isGETRequest());
  }

  @Test
  public void handlerBuildsTheCorrectResponseStatusLine() {
    Request request = new Request("GET /simple_get HTTP/1.1");
    Handler handler = new Handler(request);

    assertEquals("HTTP/1.1 200 OK\r\n", handler.buildResponse());
  }

}