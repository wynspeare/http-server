package client;

public class Client {
    private IClientWrapper client;

    public Client(IClientWrapper client) {
        this.client = client;
    }

    public void start(int port) {
        client.create(port);
        String messageToSend;
        while ((messageToSend = client.getUserInput()) != null) {
            if (messageToSend.toLowerCase().trim().equals("close")) {
                System.out.println("Closing Client");
                client.sendData(messageToSend);
                break;
            } else {
                client.sendData(messageToSend);
                client.receiveData();
            }
        }
        client.close();
    }

    public static void main(String[] args) {
        ClientWrapper clientWrapper = new ClientWrapper();
        Client client = new Client(clientWrapper);
        client.start(5000);
    }
}