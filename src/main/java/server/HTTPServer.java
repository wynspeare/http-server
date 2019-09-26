package server;

import server.handlers.DefaultHandler;
import server.handlers.EchoHandler;
import server.handlers.RedirectHandler;
import server.logger.ServerLogger;
import server.wrappers.IServerSocketWrapper;
import server.wrappers.ISocketWrapper;
import server.wrappers.ServerSocketWrapper;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {
    public IServerSocketWrapper serverSocket;
    private ExecutorService pool;
    public static final ServerLogger serverLogger = createLogger("Logs");


    public HTTPServer() {
        serverSocket = new ServerSocketWrapper(5000);
        int processingCoresAvailable = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(processingCoresAvailable);
    }

    public static ServerLogger createLogger(String directory) {
        return new ServerLogger(directory);
    }

    public Runnable createRunnable(Router router) {
        ISocketWrapper socket = null;
        try {
            socket = serverSocket.acceptConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServerRunnable(socket, router);
    }

    public void serve(Router router) {
        while (!serverSocket.isClosed()) {
            Thread thread = new Thread(createRunnable(router));
            pool.execute(thread);
        }
    }

    public void close() {
        pool.shutdown();
        serverSocket.close();
    }
}


//package server;
//
//import server.handlers.DefaultHandler;
//import server.handlers.EchoHandler;
//import server.handlers.RedirectHandler;
//import server.logger.ServerLogger;
//import server.wrappers.IServerSocketWrapper;
//import server.wrappers.ISocketWrapper;
//import server.wrappers.ServerSocketWrapper;
//import java.io.IOException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//
//public class HTTPServer {
//    public IServerSocketWrapper serverSocket;
//    private ExecutorService pool;
//    public static final ServerLogger serverLogger = createLogger("Logs");
//
//    public HTTPServer(IServerSocketWrapper serverSocket, ExecutorService executorService) {
//        this.serverSocket = serverSocket;
//        this.pool = executorService;
//    }
//
//    public static void main(String[] args) {
//        IServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(5000);
//        int processingCoresAvailable = Runtime.getRuntime().availableProcessors();
//        HTTPServer server = new HTTPServer(serverSocketWrapper, Executors.newFixedThreadPool(processingCoresAvailable));
//        Router router = createRouter();
//        server.serve(router);
//    }
//
//    public static ServerLogger createLogger(String directory) {
//        return new ServerLogger(directory);
//    }
//
//    public static Router createRouter() {
//        Router router = new Router(serverLogger);
//
//        router.addRoute("GET", "/simple_get", new DefaultHandler());
//        router.addRoute("HEAD", "/simple_get", new DefaultHandler());
//        router.addRoute("HEAD", "/get_with_body", new DefaultHandler());
//        router.addRoute("POST", "/echo_body", new EchoHandler());
//        router.addRoute("GET", "/redirect", new RedirectHandler("http://127.0.0.1:5000/simple_get"));
//        router.addRoute("GET", "/test", new DefaultHandler());
//
//        return router;
//    }
//
//    public Runnable createRunnable(Router router) {
//        ISocketWrapper socket = null;
//        try {
//            socket = serverSocket.acceptConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ServerRunnable(socket, router);
//    }
//
//    public void serve(Router router) {
//        while (!serverSocket.isClosed()) {
//            Thread thread = new Thread(createRunnable(router));
//            pool.execute(thread);
//        }
//    }
//}
