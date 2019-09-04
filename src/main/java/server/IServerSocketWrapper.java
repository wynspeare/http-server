package server;

import java.io.IOException;

public interface IServerSocketWrapper {
  ISocketWrapper acceptConnection() throws IOException;
  boolean isClosed();
}

