package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWrapper implements ISocketWrapper {
  private BufferedReader input;
  private PrintWriter output;

  public SocketWrapper(Socket socket) throws IOException {
    this.input = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
    this.output = new PrintWriter(socket.getOutputStream(), true);
  }

  public String receiveData() {
    try {
      String clientMessage = input.readLine();
      return clientMessage;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public void sendData(String data) {
    output.println(data);
  }

  public void close() {
    try {
      output.close();
      input.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
