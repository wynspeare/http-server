package server.wrappers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements IServerSocketWrapper {
    private ServerSocket serverSocket;

    public ServerSocketWrapper(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Awaiting connection");
    }

    public ISocketWrapper acceptConnection() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Accepted connection");
        return new SocketWrapper(socket);
    }

    public boolean isClosed() {
        return serverSocket.isClosed();
    }
}