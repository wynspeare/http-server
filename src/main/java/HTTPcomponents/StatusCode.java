package HTTPcomponents;

public enum StatusCode {
  OK (200),
  NOT_FOUND (404),
  ;

  public final int code;

  StatusCode(int code) {
    this.code = code;
  }
}
