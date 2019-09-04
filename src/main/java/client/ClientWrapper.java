package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWrapper implements IClientWrapper {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private InputStreamReader streamReader;

    public void create(int port) {
        try {
            socket = new Socket("127.0.0.1", port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUserInput() {
        String consoleInput;
        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            consoleInput = consoleReader.readLine();
            return consoleInput;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void sendData(String data) {
        writer.println(data);
    }

    public String receiveData() {
        try {
            String serverResponse = reader.readLine();
            System.out.println("Echo from server: " + serverResponse);
            return serverResponse;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            writer.close();
            reader.close();
            streamReader.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
