package server;

import org.junit.Test;
import server.request.Request;
import server.request.Handler;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


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
    Response response = handler.buildResponse();

    assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
  }

  @Test
  public void handlerChecksIfRequestIsAHEAD() {
    Request request = new Request("HEAD /simple_get HTTP/1.1");
    Handler handler = new Handler(request);

    assertTrue(handler.isHEADRequest());
  }

  @Test
  public void handlerBuildsTheCorrectResponseStatusLineFromAHeadRequest() {
    Request request = new Request("HEAD /simple_get HTTP/1.1");
    Handler handler = new Handler(request);
    Response response = handler.buildResponse();

    assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
  }

  @Test
  public void handlerChecksIfRequestIsAPOST() {
    Request request = new Request("POST /random_path HTTP/1.1");
    Handler handler = new Handler(request);

    assertTrue(handler.isPOSTRequest());
  }

  @Test
  public void handlerBuildsTheCorrectResponseStatusLineFromAPOSTRequest() {
    String incomingRequest = "POST /simple_get HTTP/1.1\n" +
            "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n" +
            "Accept: */*\n" +
            "User-Agent: Ruby\n" +
            "Connection: close\n" +
            "Host: 127.0.0.1:5000\n" +
            "Content-Length: 32\n" +
            "Content-Type: application/x-www-form-urlencoded\n" +
            "\r\n" +
            "some body that could be anything";
    Request request = new Request(incomingRequest);

    Handler handler = new Handler(request);
    Response response = handler.buildResponse();

    assertEquals("HTTP/1.1 200 OK\r\nsome body that could be anything", response.getStatusLine());
  }
}