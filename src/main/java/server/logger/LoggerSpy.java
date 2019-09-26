package server.logger;

public class LoggerSpy implements ILogger {
  private String loggedMessage;

  public void log(String message, String method) {
    loggedMessage = "\"" + method + "\" - " + message;
  }

  public String getLoggedMessage() {
    return loggedMessage;
  }
}
