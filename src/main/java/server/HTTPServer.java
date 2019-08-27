package server;

public class HTTPServer {
    private SocketWrapper socket;

    public HTTPServer(SocketWrapper socket) {
        this.socket = socket;
    }

    public void start(int port) {
        try {
            socket.createAndListen(port);

            String clientMessage;
            while ((clientMessage = socket.receiveData()) != null) {
                System.out.println("Client message received by server: " + clientMessage);
                socket.sendData(clientMessage);
            }
        } finally {
            socket.close();
        }
    }

    public static void main(String[] args) {
        ServerSocketWrapper serverWrapper = new ServerSocketWrapper();
        HTTPServer server = new HTTPServer(serverWrapper);
        server.start(4242);
    }
}
