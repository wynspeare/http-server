package server;

public class Response {
  int statusCode;
  String statusLine;


  public void build() {
    statusCode = 200;
    statusLine = "HTTP/1.1\n" + statusCode + "\nOK\r\n";
  }

  public int getStatusCode() {
    return statusCode;
  }
}
