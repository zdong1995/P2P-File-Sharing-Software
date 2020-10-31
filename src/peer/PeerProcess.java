package peer;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PeerProcess extends Peer implements Runnable {

  private ArrayList<Integer> connectedPeers;

  public PeerProcess(int peerID, boolean hasFile, String hostName, int portNum) {
    super(peerID, hasFile, hostName, portNum);
    this.connectedPeers = new ArrayList<>();
    System.out.println("hasFile=" + hasFile + ", this.hasFile=" + this.hasFile);
    System.out.println("creat a peer with peerID=" + peerID);
  }

  public void makeConnection() throws Exception { // TODO
    try {
      // create a socket to connect to the server
      Socket hostSocket = new Socket("localhost", this.getPortNum());
      System.out.println("Connecting to localhost in port " + Integer.toString(this.portNum));
      String message_out = "P2PFILESHARINGPROJ" + "0000000000" + Integer.toString(this.peerID); // Test only
      System.out.println("message_out=" + message_out);
      this.connector.sendMessage(message_out, hostSocket);
    } catch (ConnectException e) {
      System.err.println("Connection refused. You need to initiate a server first.");
    } catch (UnknownHostException unknownHost) {
      System.err.println("You are trying to connect to an unknown host!");
    } catch (IOException ioException) {
      ioException.printStackTrace();
    } finally {
      // Close connections
      try {
        hostSocket.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  public void startSender() { // TODO

  }

  public void startListener() { // TODO

  }

  @Override
  public void run() {

  }
}
