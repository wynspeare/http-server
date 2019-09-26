package server.wrappers;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServerSocketWrapperSpy implements IServerSocketWrapper {
  private boolean acceptConnectionCalled = false;
  public SocketWrapperSpy socketWrapper;

  public ServerSocketWrapperSpy(BufferedReader input, PrintWriter output) {
    injectSocketWrapper(input, output);
  }

  public SocketWrapperSpy acceptConnection() {
    acceptConnectionCalled = true;
    return socketWrapper;
  }

  public boolean isClosed() {
    return false;
  }

  public void close() { }

  public void injectSocketWrapper(BufferedReader input, PrintWriter output) {
    socketWrapper = new SocketWrapperSpy(input, output);
  }

  public boolean wasAcceptConnectionCalled() {
    return acceptConnectionCalled;
  }
}
