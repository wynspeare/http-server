package server.request;

import HTTPcomponents.Methods;

import java.util.HashMap;

public class Request {
  String incomingRequest;

  public Request(String incomingRequest) {
    this.incomingRequest = incomingRequest;
  }

  public String getRequestMethod() {
    return incomingRequest.split(" ")[0];
  }

  public Methods getMethod() {
    return Methods.valueOf(getRequestMethod());
  }

  public String getRequestPath() {
    return incomingRequest.split(" ")[1];
  }

  public String getRequestVersion() {
    return incomingRequest.split(" ")[2];
  }

  public HashMap<String, String> getRequestHeaders(ParseHeaders parseHeaders) {
    return parseHeaders.getHeaderKeyValuePairs(parseHeaders.splitRequest(incomingRequest));
  }
}
