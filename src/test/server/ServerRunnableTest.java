package server;

import org.junit.Test;
import server.wrappers.SocketWrapperSpy;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class ServerRunnableTest {

  @Test
  public void runnableListensAndRespondsForSimpleGet() {

    BufferedReader input = new BufferedReader(
            new StringReader("GET /simple_get HTTP/1.1\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    SocketWrapperSpy socketWrapperSpy = new SocketWrapperSpy(input, output);

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy);
    runnable.run();

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());
  }

  @Test
  public void runnableListensAndRespondsForSimpleGetWithHeaders() {
    String incomingRequest = "GET /simple_get HTTP/1.1\n" +
            "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n" +
            "Accept: */*\n" +
            "User-Agent: Ruby\n" +
            "Connection: close\n" +
            "Host: 127.0.0.1:5000";
    BufferedReader input = new BufferedReader(
            new StringReader(incomingRequest));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    SocketWrapperSpy socketWrapperSpy = new SocketWrapperSpy(input, output);

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy);
    runnable.run();

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());
  }

  @Test
  public void runnableListensAndRespondsForGetRequestFromAlternatePath() {

    BufferedReader input = new BufferedReader(
            new StringReader("GET /test HTTP/1.1\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    SocketWrapperSpy socketWrapperSpy = new SocketWrapperSpy(input, output);

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy);
    runnable.run();

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());
  }

  @Test
  public void twoRunnablesListenAndRespondForSimpleGet() {

    BufferedReader input = new BufferedReader(
            new StringReader("GET /simple_get HTTP/1.1\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    SocketWrapperSpy socketWrapperSpy = new SocketWrapperSpy(input, output);

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy);
    runnable.run();

    BufferedReader input2 = new BufferedReader(
            new StringReader("GET /simple_get HTTP/1.1\n"));
    SocketWrapperSpy socketWrapperSpy2 = new SocketWrapperSpy(input2, output);

    ServerRunnable secondRunnable = new ServerRunnable(socketWrapperSpy2);
    secondRunnable.run();

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy2.getSentData());
    assertTrue(socketWrapperSpy2.wasCloseCalled());
  }

}