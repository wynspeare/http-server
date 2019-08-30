package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class ServerSocketWrapperSpy implements SocketWrapper {
    private BufferedReader input;
    private PrintWriter output;
    private boolean createAndListenCalled = false;
    private String sentData;
    private boolean closeCalled = false;

    public ServerSocketWrapperSpy(
            BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }
    public void acceptConnection(ServerSocket serverSocket) {
        createAndListenCalled = true;
    }

    public String receiveData() {
        try {
            return input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading mock socket input");
        }
        return "";
    }

    public void sendData(String data) {
        output.println(data.toUpperCase());
        sentData = data.toUpperCase();
    }

    public void close() {
        closeCalled = true;
    }
    public boolean wasCreateAndListenCalled() {
        return createAndListenCalled;
    }
    public String getSentData() {
        return sentData;
    }
    public boolean wasCloseCalled() {
        return closeCalled;
    }
}