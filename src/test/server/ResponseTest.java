package server;

import HTTPcomponents.StatusCode;
import org.junit.Test;

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

}