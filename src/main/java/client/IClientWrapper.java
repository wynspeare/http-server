package client;

public interface IClientWrapper {
  void create(int port);
  String getUserInput();
  void sendData(String data);
  String receiveData();
  void close();
}
