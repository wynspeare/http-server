package server.request;

import java.util.HashMap;

public class Request {
  String incomingRequest;

  public Request(String incomingRequest) {
    this.incomingRequest = incomingRequest;
  }

  public String getRequestMethod() {
    return incomingRequest.split(" ")[0];
  }

  public String getRequestPath() {
    return incomingRequest.split(" ")[1];
  }

  public String getRequestVersion() {
    return incomingRequest.split(" ")[2];
  }

//  public HashMap<String, String> getRequestHeaders(Headers headers) {
//    headers.splitHeadersFromRequest(incomingRequest);
//
//  }
}
