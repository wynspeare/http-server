package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper {
    private BufferedReader input;
    private PrintWriter output;

    public void acceptConnection(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            System.out.println("Accepted connection");
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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