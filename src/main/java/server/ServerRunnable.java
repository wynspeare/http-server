package server;

import server.request.Request;
import server.request.Handler;

public class ServerRunnable implements Runnable {
    public ISocketWrapper socketWrapper;

    public ServerRunnable(ISocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

//    public void run( ) {
//        try {
//            String clientMessage;
//            while ((clientMessage = socketWrapper.receiveData()) != null) {
//                if (clientMessage.toLowerCase().trim().equals("hello")) {
//                    break;
//                } else {
//                    System.out.println("Message received by server: " + clientMessage);
//                    socketWrapper.sendData(clientMessage);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("Closing Socket!");
//            socketWrapper.close();
//        }
//    }

    public void run( ) {
        try {
            String clientMessage = socketWrapper.receiveData();
            if (clientMessage != null) {
//            while ((clientMessage = socketWrapper.receiveData()) != null) {
                Request request = new Request(clientMessage);
                Handler handler = new Handler(request);
                String response = handler.buildResponse();
                System.out.println("Message received by server: " + clientMessage);
                System.out.println("RESPONSE: " + response);
                socketWrapper.sendData(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing Socket!");
            socketWrapper.close();
        }
    }

}