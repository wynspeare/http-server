package server;

import HTTPcomponents.StatusCode;
import org.junit.Test;
import server.request.Request;

import static org.junit.Assert.*;

public class ResponseTest {
  @Test
  public void responseBuildsResponseStatusLineWithProvidedStatusCodeFor200() {
    Response response = new Response.Builder()
            .withStatusLine(StatusCode.OK)
            .build();

    assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.stringifyResponse());
  }

  @Test
  public void responseCanCheckTheStatusLineOfABuiltResponse() {
    Response response = new Response.Builder()
            .withStatusLine(StatusCode.OK)
            .build();

    assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
  }

  @Test
  public void responseBuildsResponseWithReceivedBody() {
    String incomingRequest = "POST /simple_get HTTP/1.1\r\n" +
            "Content-Length: 32\n" +
            "Content-Type: application/x-www-form-urlencoded\n" +
            "\r\n\r\n" +
            "some body that could be anything";
    Request request = new Request(incomingRequest);

    Response response = new Response.Builder()
            .withStatusLine(StatusCode.OK)
            .withHeader("Content-Length: " + request.getRequestBody().length())
            .withBody(request.getRequestBody())
            .build();

    assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 32\r\n\r\nsome body that could be anything", response.stringifyResponse());
  }
}