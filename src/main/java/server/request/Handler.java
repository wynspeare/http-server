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
    if (request.getRequestMethod().equals(Methods.GET.toString())) {
      System.out.println("GET Request!");
      return true;
    }
    return false;
  }

  public String buildResponse() {
    if (isGETRequest()) {
      Response response = new Response();
      response.build(StatusCode.OK);
      return response.getStatusLine();
    }
    return null;
  }

}
