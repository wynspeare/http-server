package server;

import HTTPcomponents.Methods;
import org.junit.Test;
import server.request.ParseHeaders;
import server.request.Request;

import java.util.Map;

import static HTTPcomponents.StatusLineComponents.VERSION;
import static org.junit.Assert.*;

public class RequestTest {
  @Test
  public void requestTakesInAStatusLineAndReturnsTheHTTPMethod() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("GET", request.getRequestMethod());
  }

  @Test
  public void requestTakesInAStatusLineAndReturnsTheRoute() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("/simple_get", request.getRequestPath());
  }

  @Test
  public void requestTakesInAStatusLineAndReturnsVersion() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals("HTTP/1.1", request.getRequestVersion());
  }

  @Test
  public void requestTakesInAStatusLineAndReturnsVersionUsingConstant() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals(VERSION, request.getRequestVersion());
  }

  @Test
  public void requestMatchsIncomingRequestToEnumHTTPMethod() {
    Request request = new Request("GET /simple_get HTTP/1.1");

    assertEquals(Methods.GET.toString(), request.getRequestMethod());
  }

  @Test
  public void requestRetrievesHeadersInMap() {
    String incomingRequest = "GET /simple_get HTTP/1.1\n" +
            "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n" +
            "Accept: */*\n" +
            "User-Agent: Ruby\n" +
            "Connection: close\n" +
            "Host: 127.0.0.1:5000";
    Request request = new Request(incomingRequest);

    Map<String, String> expectedHeaders = Map.of(
            "Accept-Encoding", "gzip;q=1.0,deflate;q=0.6,identity;q=0.3",
            "Accept", "*/*",
            "User-Agent", "Ruby",
            "Connection", "close",
            "Host", "127.0.0.1:5000");

    ParseHeaders parseHeaders = new ParseHeaders();

    assertEquals(expectedHeaders, request.getRequestHeaders(parseHeaders));
  }
}