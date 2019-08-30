package server;

public class ServerRunnable implements Runnable {
  private SocketWrapper socketWrapper;

  public ServerRunnable(SocketWrapper socketWrapper) {
    this.socketWrapper = socketWrapper;
  }

  public void run( ) {
    try {
      String clientMessage;
      while ((clientMessage = socketWrapper.receiveData()) != null) {
        if (clientMessage.toLowerCase().trim().equals("close")) {
          break;
        } else {
          System.out.println("Client message received by server: " + clientMessage);
          socketWrapper.sendData(clientMessage);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println("Closing Socket!");
      socketWrapper.close();
    }
  }
}

