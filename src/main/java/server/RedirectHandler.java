package server;

import HTTPcomponents.StatusCode;

public class RedirectHandler implements IHandler {
  @Override
  public Response buildResponse() {
    Response response = new Response();
    response.build(StatusCode.Moved_Permanently);
    return response;
  }
}
