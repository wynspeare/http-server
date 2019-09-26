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
        this.serverSocket = new ServerSocketWrapper(5000);;
        int processingCoresAvailable = Runtime.getRuntime().availableProcessors();
        this.pool = Executors.newFixedThreadPool(processingCoresAvailable);
    }

//    public static void main(String[] args) {
//        HTTPServer server = new HTTPServer();
//        server.serverSocket = new ServerSocketWrapper(5000);;
//        int processingCoresAvailable = Runtime.getRuntime().availableProcessors();
//        server.pool = Executors.newFixedThreadPool(processingCoresAvailable);
//    }

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