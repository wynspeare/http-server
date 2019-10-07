package server;

import HTTPcomponents.StatusCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static HTTPcomponents.StatusLineComponents.CRLF;
import static HTTPcomponents.StatusLineComponents.VERSION;
import static HTTPcomponents.StatusLineComponents.SP;

public class Response {
  private String statusLine;
  private String headers;
  private byte[] body;

  public String getStatusLine() {
    return statusLine;
  }

  public String stringifyResponse() {
    String bodyAsString = new String(body);
    return statusLine + headers + CRLF + bodyAsString;
  }

  public byte[] getResponseAsBytes() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      outputStream.write(statusLine.getBytes());
      outputStream.write(headers.getBytes());
      outputStream.write(CRLF.getBytes());
      outputStream.write(body == null ? "".getBytes() : body);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return outputStream.toByteArray();
  }

  public static class Builder {
    private int statusCode;
    private Response response;
    private String statusLine;
    private String headers;
    private byte[] body;

    public Builder(){
      this.response = new Response();
      this.headers = "";
      this.body = new byte[] {};
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

    public Builder withBody(byte[] body) {
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
