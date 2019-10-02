package server.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.request.Request;

public class DefaultHandler implements IHandler {
  @Override
  public Response buildResponse(Request request) {
    Response response = new Response.Builder()
            .withStatusLine(StatusCode.OK)
            .build();
    return response;
  }
}
