package server.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.request.Request;

public class PostHandler implements IHandler {
  @Override
  public Response buildResponse(Request request) {
    Response response = new Response();
    response.build(StatusCode.OK, request.getRequestBody());
    return response;
  }
}
