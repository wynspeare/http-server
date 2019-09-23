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

    public HTTPServer(IServerSocketWrapper serverSocket, ExecutorService executorService) {
        this.serverSocket = serverSocket;
        pool = executorService;
    }

    public static void main(String[] args) {
        IServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(5000);
        int processingCoresAvailable = Runtime.getRuntime().availableProcessors();
        HTTPServer server = new HTTPServer(serverSocketWrapper, Executors.newFixedThreadPool(processingCoresAvailable));
        Router router = createRouter();
        server.serve(router);
    }

    public static Router createRouter() {
        Router router = new Router();

        router.addRoute("GET", "/simple_get");
        router.addRoute("HEAD", "/simple_get");
        router.addRoute("HEAD", "/get_with_body");
        router.addRoute("POST", "/echo_body");
        router.addRoute("GET", "/redirect");
        router.addRoute("GET", "/test");
        return router;
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
}
