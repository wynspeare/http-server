package server.utils;

public class InvalidRequestException extends Exception {
  public InvalidRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
