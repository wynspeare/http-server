package server;

public class Response {
  int statusCode;
  public String statusLine;


  public void build() {
    statusCode = 200;
    statusLine = "HTTP/1.1 " + statusCode + " OK\r\n";
  }

  public int getStatusCode() {
    return statusCode;
  }
}
