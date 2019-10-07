package server.wrappers;

public interface ISocketWrapper {
    String receiveData();
    void sendData(byte[] data);
    void close();
}
