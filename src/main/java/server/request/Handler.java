package server.request;

import HTTPcomponents.Methods;
import HTTPcomponents.StatusCode;
import server.Response;

public class Handler {
  Request request;

  public Handler(Request request) {
    this.request = request;
  }

  public Boolean isGETRequest() {
    return request.getRequestMethod().equals(Methods.GET.toString());
  }

  public Boolean isHEADRequest() {
    return request.getRequestMethod().equals(Methods.HEAD.toString());
  }

  public Response buildResponse() {
    Response response = new Response();
    if (isGETRequest() || isHEADRequest()) {
      response.build(StatusCode.OK);
    }
    return response;
  }
}
