package server;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {
  @Test
  public void responseCanGetStatusCode() {
    Response response = new Response();
    response.build();

    assertEquals(200, response.getStatusCode());
  }

}