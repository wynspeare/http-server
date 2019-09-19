package server;

import HTTPcomponents.StatusCode;

public class GetHandler implements IHandler {
  @Override
  public Response buildResponse() {
    Response response = new Response();
    response.build(StatusCode.OK);
    return response;
  }
}
