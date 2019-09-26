package server.wrappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWrapper implements ISocketWrapper {
  private BufferedReader input;
  private PrintWriter output;

  public SocketWrapper(Socket socket) {
    try {
      this.input = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
      this.output = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String receiveData() {
    char[] dataBuffer = new char[100];
    try {
      String incomingData = "";
      while(input.ready()) {
        int value = input.read(dataBuffer);
        incomingData += new String(dataBuffer,0, value);
      }
      return incomingData;
    } catch (IOException ex) {
      ex.printStackTrace();
      return ex.toString();
    }
  }

  public void sendData(String data) {
    output.print(data);
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
