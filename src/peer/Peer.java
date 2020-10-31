package peer;

import connection.connectionHandler;

import java.net.Socket;

public class Peer {

  private int peerID; //peer id, should be distinct from each other
  private boolean hasFile;
  private String hostName;
  private int portNum; // number of listening port
  private int NumberOfPreferredNeighbors; // TODO
  private int UnchokingInterval;
  private int OptimisticUnchokingInterval;
  private String FileName;
  private int FileSize;
  private int PieceSize;
  private connectionHandler connector;
  private Socket hostSocket; // socket connect to the server

  public Peer(int peerID) {
    this.peerID = peerID;
  }

  public Peer(int peerID, boolean hasFile, String hostName, int portNum) {
    this.peerID = peerID;
    this.hasFile = hasFile;
    this.hostName = hostName;
    this.portNum = portNum;
  }

  public int getPeerID() {
    return peerID;
  }

  public boolean isHasFile() {
    return hasFile;
  }

  public int getPortNum() {
    return portNum;
  }

  public connectionHandler getConnector() {
    return connector;
  }

  public void setConnector(connectionHandler connector) {
    this.connector = connector;
  }

  public Socket getHostSocket() {
    return hostSocket;
  }

  public void setHostSocket(Socket hostSocket) {
    this.hostSocket = hostSocket;
  }
}
