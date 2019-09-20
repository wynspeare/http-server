package server;

import server.request.Request;
import server.wrappers.ISocketWrapper;

public class ServerRunnable implements Runnable {
    public ISocketWrapper socketWrapper;

    public ServerRunnable(ISocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }


    public void run() {
        try {
            String clientMessage = socketWrapper.receiveData();
            if (clientMessage != null) {
                Request request = new Request(clientMessage);

                // Building router in runnable with hardcoded routes
                // Will move to HTTPServer and be injected into runnable
                Router router = new Router();
                router.addRoute("GET", "/simple_get");
//                router.addRoute("HEAD", "/simple_get");
//                router.addRoute("HEAD", "/get_with_body");
                router.addRoute("GET", "/redirect");
                router.addRoute("GET", "/test");


                Response response = router.handle(request);
                System.out.println("Message received by server: " + clientMessage);
                System.out.println("RESPONSE: " + response);
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