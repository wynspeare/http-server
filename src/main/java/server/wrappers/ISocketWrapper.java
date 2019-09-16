package server.wrappers;

public interface ISocketWrapper {
    String receiveData();
    void sendData(String data);
    void close();
}
