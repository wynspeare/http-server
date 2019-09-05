package server;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class ServerRunnableTest {

  @Test
  public void runnableListensForSimpleGet() {

    BufferedReader input = new BufferedReader(
            new StringReader("GET /simple_get HTTP/1.1\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    SocketWrapperSpy socketWrapperSpy = new SocketWrapperSpy(input, output);

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy);
    runnable.run();

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());
  }

}