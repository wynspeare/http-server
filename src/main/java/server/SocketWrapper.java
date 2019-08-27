package server;

public interface SocketWrapper {
    void createAndListen(int port);
    String receiveData();
    void sendData(String data);
    void close();
}
