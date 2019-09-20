package server;

import server.wrappers.IServerSocketWrapper;
import server.wrappers.ServerSocketWrapper;

import java.io.IOException;

public class HTTPServer {
    public IServerSocketWrapper serverSocket;

    public HTTPServer(IServerSocketWrapper serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws Exception {
        IServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(5000);
        HTTPServer server = new HTTPServer(serverSocketWrapper);
        server.serve();
    }

    public Runnable createRunnable() throws IOException {
        return new ServerRunnable(serverSocket.acceptConnection());
    }

    public void serve() throws IOException {
        while (!serverSocket.isClosed()) {
            Thread thread = new Thread(createRunnable());
                thread.start();
        }
    }
}
