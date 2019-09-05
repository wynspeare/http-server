package server;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class HTTPServerTest {

  @Test
  public void testReceivedDataIsEchoedBackInSentData() {

    BufferedReader input = new BufferedReader(
            new StringReader("echo\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    ServerSocketWrapperSpy serverSocketWrapperSpy = new ServerSocketWrapperSpy(input, output);

    HTTPServer httpServer = new HTTPServer(serverSocketWrapperSpy);

    try {
      ServerRunnable runnable = (ServerRunnable) httpServer.createRunnable();
      runnable.run();
      SocketWrapperSpy socketWrapperSpy = ((SocketWrapperSpy) runnable.socketWrapper);

      assertTrue(serverSocketWrapperSpy.wasAcceptConnectionCalled());
      assertEquals("ECHO", socketWrapperSpy.getSentData());
      assertTrue(socketWrapperSpy.wasCloseCalled());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}