package HTTPcomponents;

public enum StatusCode {
  OK (200),
  NOT_FOUND (404),
  Moved_Permanently (301)
  ;

  public final int code;

  StatusCode(int code) {
    this.code = code;
  }
}
