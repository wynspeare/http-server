package server.request;

import HTTPcomponents.Methods;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import static HTTPcomponents.StatusLineComponents.CRLF;

public class Request {
  public String incomingRequest;

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

  public HashMap<String, String> getRequestHeaders() {
    ParseHeaders parseHeaders = new ParseHeaders();

    String headers = incomingRequest.split(CRLF)[1];
    return parseHeaders.getHeaderKeyValuePairs(parseHeaders.splitRequest(headers));
  }

  public String getRequestBody() {
    return incomingRequest.split(CRLF + CRLF, 2)[1];
  }

  public byte[] getRequestBodyAsBytes() {
    return getRequestBody().getBytes(StandardCharsets.UTF_8);
  }
}

