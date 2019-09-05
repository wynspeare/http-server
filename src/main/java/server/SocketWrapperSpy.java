package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SocketWrapperSpy implements ISocketWrapper {
    private BufferedReader input;
    private PrintWriter output;
    private String sentData;
    private boolean closeCalled = false;

    public SocketWrapperSpy(
            BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
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
    public String getSentData() {
        return sentData;
    }
    public boolean wasCloseCalled() {
        return closeCalled;
    }
}