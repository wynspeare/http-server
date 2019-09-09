package server;

import HTTPcomponents.StatusCode;

import static HTTPcomponents.StatusLineComponents.CRLF;
import static HTTPcomponents.StatusLineComponents.VERSION;
import static HTTPcomponents.StatusLineComponents.SP;


public class Response {
  private int statusCode;
  private String statusLine;


  public void build(StatusCode statusCode) {
    this.statusCode = statusCode.code;
    statusLine = VERSION + SP + this.statusCode + SP + statusCode + CRLF;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getStatusLine() {
    return statusLine;
  }
}
