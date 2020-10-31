package peer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PeerProcess extends Peer implements Runnable {

  private ArrayList<Integer> connectedPeers;
  private ServerSocket listener;
  ObjectOutputStream out;
  ObjectInputStream in;

  public PeerProcess(int peerID, boolean hasFile, String hostName, int portNum) {
    super(peerID, hasFile, hostName, portNum);
    this.connectedPeers = new ArrayList<>();
    System.out.println("hasFile=" + hasFile + ", this.hasFile=" + this.hasFile);
    System.out.println("creat a peer with peerID=" + peerID);
  }

  public void makeConnection(int anotherPeerID) { // TODO
    System.out.println("make connection to another peerID=" +
        Integer.toString(anotherPeerID));
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
    try {
      listener = new ServerSocket(this.portNum);
      Socket connection = listener.accept();
      while (true) { // TODO
        if (connection != null) {
          System.out.println("Connecting to client");
          out = new ObjectOutputStream(connection.getOutputStream());
          out.flush();
          in = new ObjectInputStream(connection.getInputStream());
          String message_in = (String) in.readObject();
          System.out.println("receive message " + message_in + ", length=" + message_in.length());

          // check the correctness of handshake
          if (message_in.length() == 32) {
            String header = message_in.substring(0, 18);
            if (header.equals("P2PFILESHARINGPROJ")) {
              int anotherPeerID = Integer.parseInt(message_in.substring(28, 32));
              if (!connectedPeers.contains(anotherPeerID)) {
                connectedPeers.add(anotherPeerID);
                System.out.println("handshake header correct, peer " + anotherPeerID + " is added to connected list");
                makeConnection(anotherPeerID);
              } else {
                System.out.println("handshake header correct, but this peer is already connected");
              }
            }
          }
        }
      }
    } catch (ClassNotFoundException classnot) {
      System.err.println("Data received in unknown format");
    } catch (IOException ioException) {
      System.out.println("Disconnect with Client 1");
    } finally {
      // Close connections
      try {
        in.close();
        out.close();
      } catch (IOException ioException) {
        System.out.println("Disconnect with Client 2");
      }
    }
  }

  @Override
  public void run() {

  }
}
