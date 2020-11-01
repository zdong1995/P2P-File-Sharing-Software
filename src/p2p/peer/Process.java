package p2p.peer;

import p2p.config.CommonConfig;
import p2p.connection.ConnectionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;

public class Process extends Peer implements Runnable {

  /**
   * PeerProcess inherited Peer to be Runnable Thread related to
   */
  private Map<Integer, Peer> connectedPeers;
  private ServerSocket listener;

  ObjectOutputStream out;
  ObjectInputStream in;

  public Process(int peerID, String hostName, int portNum, boolean hasFile) {
    super(peerID, hasFile, hostName, portNum);
    connectedPeers = new HashMap<>();
  }

  public Process(int peerID, CommonConfig config) {
    super(peerID, config);
    connectedPeers = new HashMap<>();
  }

  /**
   * Establish connection between host peer and neighbor peer by creating
   * connection handler thread and update the connector field of neighbour
   *
   * @param peerId peerId of neighbor peer
   */
  public void makeConnection(int peerId) { // TODO
    System.out.println("make p2p connection to another peerID = " +
        peerId);

    // TODO: Handshake

    Peer neighbor = connectedPeers.get(peerId);
    ConnectionHandler connector = new ConnectionHandler(this, neighbor, out, in);
    connector.start();
    // neighbor.setConnector(connector);

    /*
    try {
      // create a socket to connect to the server
      Socket hostSocket = new Socket("localhost", this.getPortNum());
      System.out.println("Connecting to localhost in port " + this.portNum);
      String message_out = "P2PFILESHARINGPROJ" + "0000000000" + this.peerID; // p2p.Test only
      System.out.println("message_out=" + message_out);
      this.connector.sendMessage(message_out, hostSocket);
      // handshake message

      // create connection

      // send bitfield
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
    */
  }


  public void startSender() { // TODO
    System.out.println("Starting Sender");
    /*
    new Thread() {
      public void run() {
        try {

        } catch () {

        }
      }
    }.start();
    */
  }

  /**
   * Initialize Peer Process to create one Thread and internally call
   * start() method to run the Thread
   */
  public void initialize() {
    Thread t = new Thread(this);
    System.out.println("Peer Process with PeerId = " + this.peerID + "is Running");
    t.start();
  }

  /**
   * Terminate thread when peer all received completed files
   */
  public void terminate() { // TODO
    new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(50000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }.start();
  }
  /**
   * Start listener on a socket to accept handshake message
   *
   */
  public void startListener() { // TODO
    System.out.println("Starting Listener");
    new Thread() {
      @Override
      public void run() {
        try {
          listener = new ServerSocket(portNum);
          Socket connection = listener.accept();
          while (!listener.isClosed()) { // TODO
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
                  if (!connectedPeers.containsKey(anotherPeerID)) {
                    connectedPeers.put(anotherPeerID, new Peer(anotherPeerID));
                    System.out.println("handshake header correct, p2p.peer " + anotherPeerID + " is added to connected list");
                    makeConnection(anotherPeerID);
                  } else {
                    System.out.println("handshake header correct, but this p2p.peer is already connected");
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
    }.start();
  }

  @Override
  public String toString() {
    return "PeerProcess Information: " + "peerID = " + this.peerID +
        ", hasFile = " + this.isHasFile() + ", hasFile = " +
        this.isHasFile() + ", portNumber = " + this.getPortNum();
  }

  @Override
  public void run() {
    System.out.println("Starting p2p.peer " + peerID);
    startListener();
    // startSender();
    // other
  }
}
