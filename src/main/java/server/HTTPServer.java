package server;

import server.wrappers.IServerSocketWrapper;
import server.wrappers.ISocketWrapper;
import server.wrappers.ServerSocketWrapper;

import java.io.IOException;

public class HTTPServer {
    public IServerSocketWrapper serverSocket;

    public HTTPServer(IServerSocketWrapper serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) {
        IServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(5000);
        HTTPServer server = new HTTPServer(serverSocketWrapper);
        server.serve();
    }

    public Runnable createRunnable() {
        ISocketWrapper socket = null;
        try {
            socket = serverSocket.acceptConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServerRunnable(socket);
    }

    public void serve() {
        while (!serverSocket.isClosed()) {
            Thread thread = new Thread(createRunnable());
            thread.start();
        }
    }
}
