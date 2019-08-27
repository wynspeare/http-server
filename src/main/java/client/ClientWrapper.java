package client;

public interface ClientWrapper {
    void create(int port);
    String getUserInput();
    void sendData(String data);
    String receiveData();
    void close();
}
