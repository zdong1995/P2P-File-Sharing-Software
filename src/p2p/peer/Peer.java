package p2p.peer;

import p2p.connection.connectionHandler;

import java.net.Socket;

public class Peer {

  protected int peerID; // p2p.peer id, should be distinct from each other
  protected String hostName;
  protected int portNum; // number of listening port
  protected boolean hasFile;

  protected CommonConfig config;

  protected connectionHandler connector;
  protected Socket hostSocket; // socket connect to the server

  public Peer(int peerID) {
    this.peerID = peerID;
  }

  public Peer(int peerID, boolean hasFile, String hostName, int portNum) {
    this.peerID = peerID;
    this.hasFile = hasFile;
    this.hostName = hostName;
    this.portNum = portNum;
  }

  public Peer(int peerID, String hostName, int portNum, boolean hasFile, CommonConfig config) {
    this.peerID = peerID;
    this.hostName = hostName;
    this.portNum = portNum;
    this.hasFile = hasFile;
    this.config = config;
  }

  public Peer() {

  }

  public void setHostName(String s) {
    hostName = s;
  }

  public void setPortNum(int parseInt) {
    portNum = parseInt;
  }

  public int getPeerId() {
    return peerID;
  }

  public void setHasFile(boolean bool) {
    hasFile = bool;
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
