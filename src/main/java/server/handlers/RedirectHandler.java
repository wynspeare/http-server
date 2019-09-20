package server.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.request.Request;

public class RedirectHandler implements IHandler {
  @Override
  public Response buildResponse(Request request) {
    Response response = new Response();
    response.build(StatusCode.Moved_Permanently);
    response.addHeaders("Location: http://127.0.0.1:5000/simple_get");
    return response;
  }
}
