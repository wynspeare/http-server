package server.wrappers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketWrapper implements ISocketWrapper {
  private BufferedReader input;
  private DataOutputStream output;

  public SocketWrapper(Socket socket) {
    try {
      this.input = new BufferedReader( new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
      this.output = new DataOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String receiveData() {
    StringBuilder requestBuilder = new StringBuilder();
      try {
        while (input.ready() || requestBuilder.length() == 0) {
          requestBuilder.append((char) input.read());
        }
        String request = requestBuilder.toString();
        if (request.length() <= 1) {
          return null;
        } else {
          return request;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    return null;
  }


  public void sendData(byte[] data) {
    try {
      output.write(data);
      output.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
