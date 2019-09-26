package server;

import HTTPcomponents.StatusCode;
import org.junit.Test;
import server.request.Request;

import static org.junit.Assert.*;

public class ResponseTest {
  @Test
  public void responseBuildsResponseStatusLineWithProvidedStatusCodeFor200() {
    Response response = new Response();
    response.build(StatusCode.OK);

    assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
  }

  @Test
  public void responseCanCheckTheStatusCodeOfABuiltResponse() {
    Response response = new Response();
    response.build(StatusCode.OK);

    assertEquals(200, response.getStatusCode());
  }

  @Test
  public void responseBuildsResponseWithReceivedBody() {
    Response response = new Response();

    String incomingRequest = "POST /simple_get HTTP/1.1\n" +
            "Content-Length: 32\n" +
            "Content-Type: application/x-www-form-urlencoded\n" +
            "\r\n" +
            "some body that could be anything";
    Request request = new Request(incomingRequest);

    response.build(StatusCode.OK, request.getRequestBody());

    assertEquals("HTTP/1.1 200 OK\r\nsome body that could be anything", response.getStatusLine());
  }
}