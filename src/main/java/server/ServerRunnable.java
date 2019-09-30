package server;

import server.request.Request;
import server.wrappers.ISocketWrapper;

public class ServerRunnable implements Runnable {
    public ISocketWrapper socketWrapper;
    public Router router;

    public ServerRunnable(ISocketWrapper socketWrapper, Router router) {
        this.socketWrapper = socketWrapper;
        this.router = router;
    }

    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            if (clientMessage != null) {
                Request request = new Request(clientMessage);
                Response response = router.handle(request);
                System.out.println("REQUEST received by server: " + clientMessage);
                System.out.println("RESPONSE: " + response.getStatusLine());
                socketWrapper.sendData(response.getStatusLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing Socket!");
            socketWrapper.close();
        }
    }
}