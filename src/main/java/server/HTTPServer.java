package server;

import server.wrappers.IServerSocketWrapper;
import server.wrappers.ISocketWrapper;
import server.wrappers.ServerSocketWrapper;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {
    public IServerSocketWrapper serverSocket;
    private ExecutorService pool;

    public void buildServerSocket(int port) {
        serverSocket = new ServerSocketWrapper(port);
        int processingCoresAvailable = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(processingCoresAvailable);
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