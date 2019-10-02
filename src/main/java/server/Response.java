package server;

import HTTPcomponents.StatusCode;
import static HTTPcomponents.StatusLineComponents.CRLF;
import static HTTPcomponents.StatusLineComponents.VERSION;
import static HTTPcomponents.StatusLineComponents.SP;

public class Response {
  private String statusLine;
  private String headers;
  private String body;

  public String getStatusLine() {
    return statusLine;
  }

  public String stringifyResponse() {
    return statusLine + headers + CRLF + body;
  }

  public static class Builder {
    private int statusCode;
    private Response response;
    private String statusLine;
    private String headers;
    private String body;

    public Builder(){
      this.response = new Response();
      this.headers = "";
      this.body = "";
    }

    public Builder withStatusLine(StatusCode statusCodeText) {
      this.statusCode = statusCodeText.code;
      this.statusLine = VERSION + SP + this.statusCode + SP + statusCodeText + CRLF;
      return this;
    }

    public Builder withHeader(String headers) {
      this.headers = headers + CRLF;
      return this;
    }

    public Builder withBody(String body) {
      this.body = body;
      return this;
    }

    public Response build() {
      response.statusLine = this.statusLine;
      response.headers = this.headers;
      response.body = this.body;

      return response;
    }
  }
}
