package server;

import java.net.ServerSocket;

public interface SocketWrapper {
    void acceptConnection(ServerSocket serverSocket);
    String receiveData();
    void sendData(String data);
    void close();
}
