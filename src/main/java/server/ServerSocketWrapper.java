package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String clientMessage;

    public void createAndListen(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Awaiting connection");

            socket = serverSocket.accept();
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
            clientMessage = input.readLine();
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
            socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

