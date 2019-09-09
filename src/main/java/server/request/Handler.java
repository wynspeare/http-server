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

  public String buildResponse() {
    Response response = new Response();
    Methods incomingMethod = Methods.valueOf(request.getRequestMethod());
    switch(incomingMethod) {
      case GET:
      case HEAD:
        response.build(StatusCode.OK);
        break;
    }
    return response.getStatusLine();
  }
}
