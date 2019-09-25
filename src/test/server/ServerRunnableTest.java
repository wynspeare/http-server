package server;

import org.junit.Test;
import server.handlers.DefaultHandler;
import server.handlers.RedirectHandler;
import server.logger.LoggerSpy;
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

    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/simple_get", new DefaultHandler());

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy, router);
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

    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/simple_get", new DefaultHandler());

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy, router);
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

    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/simple_get", new DefaultHandler());
    router.addRoute("GET", "/test", new DefaultHandler());

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy, router);
    runnable.run();

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());
  }

  @Test
  public void runnableListensAndRespondsForSimpleHEAD() {

    BufferedReader input = new BufferedReader(
            new StringReader("HEAD /simple_get HTTP/1.1\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    SocketWrapperSpy socketWrapperSpy = new SocketWrapperSpy(input, output);

    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/simple_get", new DefaultHandler());
    router.addRoute("HEAD", "/simple_get", new DefaultHandler());

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy, router);
    runnable.run();

    assertEquals("HTTP/1.1 200 OK\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());
  }

  @Test
  public void runnableListensAndRespondsForRedirect() {

    BufferedReader input = new BufferedReader(
            new StringReader("GET /redirect HTTP/1.1\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    SocketWrapperSpy socketWrapperSpy = new SocketWrapperSpy(input, output);

    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);
    router.addRoute("GET", "/simple_get", new DefaultHandler());
    router.addRoute("GET", "/redirect", new RedirectHandler("http://127.0.0.1:5000/simple_get"));

    ServerRunnable runnable = new ServerRunnable(socketWrapperSpy, router);
    runnable.run();

    assertEquals("HTTP/1.1 301 MOVED_PERMANENTLY\r\nLOCATION: HTTP://127.0.0.1:5000/SIMPLE_GET\r\n", socketWrapperSpy.getSentData());
    assertTrue(socketWrapperSpy.wasCloseCalled());
  }

}