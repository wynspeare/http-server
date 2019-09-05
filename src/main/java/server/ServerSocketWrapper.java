package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements IServerSocketWrapper {
    private ServerSocket serverSocket;

    public ServerSocketWrapper(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Awaiting connection");
    }

    public ISocketWrapper acceptConnection() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println("Accepted connection");
        return new SocketWrapper(socket);
    }

    public boolean isClosed() {
        return serverSocket.isClosed();
    }
}