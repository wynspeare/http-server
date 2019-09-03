package server;

import java.io.IOException;
import java.net.ServerSocket;

public class HTTPServer {
    private static ServerSocket serverSocket;

    public HTTPServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) {
        start(5000);
        HTTPServer server = new HTTPServer(serverSocket);
        server.serve();
    }

    public static void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Awaiting connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Runnable createRunnable(SocketWrapper serverWrapper) {
        serverWrapper.acceptConnection(serverSocket);
        return new ServerRunnable(serverWrapper);
    }

    public void serve() {
        while (!serverSocket.isClosed()) {
            ServerSocketWrapper serverWrapper = new ServerSocketWrapper();

            Thread thread = new Thread(createRunnable(serverWrapper));
            thread.start();
        }
    }
}
