package server.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.request.Request;

public class RedirectHandler implements IHandler {
  String redirectedLocation;

  public RedirectHandler(String redirectedLocation) {
    this.redirectedLocation = redirectedLocation;
  }
  @Override
  public Response buildResponse(Request request) {
    Response response = new Response.Builder()
            .withStatusLine(StatusCode.Moved_Permanently)
            .withHeader("Location: " + redirectedLocation)
            .build();
    return response;
  }
}
