package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientWrapperSpy implements ClientWrapper {

    private PrintWriter writer;
    private BufferedReader reader;
    private boolean createCalled = false;
    private String sentData;
    private boolean closeCalled = false;

    public ClientWrapperSpy(
            BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void create(int port) {
        createCalled = true;
    }

    public String getUserInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading mock socket reader");
        }
        return "";
    }

    public void sendData(String data) {
        writer.println(data.toUpperCase());
        sentData = data.toUpperCase();
    }

    public String receiveData() {
        return sentData;
    }

    public void close() {
        closeCalled = true;
    }
    public boolean wasCreateCalled() {
        return createCalled;
    }
    public String getReceivedData() {
        return sentData;
    }
    public boolean wasCloseCalled() {
        return closeCalled;
    }
}
