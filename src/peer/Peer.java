package peer;

import connection.connectionHandler;

import java.net.Socket;

public class Peer {

  protected int peerID; //peer id, should be distinct from each other
  protected boolean hasFile;
  protected String hostName;
  protected int portNum; // number of listening port
  protected int NumberOfPreferredNeighbors; // TODO
  protected int UnchokingInterval;
  protected int OptimisticUnchokingInterval;
  protected String FileName;
  protected int FileSize;
  protected int PieceSize;
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

  public Peer() {

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

  public void setNumberOfPreferredNeighbors(int numberOfPreferredNeighbors) {
    NumberOfPreferredNeighbors = numberOfPreferredNeighbors;
  }

  public void setUnchokingInterval(int unchokingInterval) {
    UnchokingInterval = unchokingInterval;
  }

  public void setOptimisticUnchokingInterval(int optimisticUnchokingInterval) {
    OptimisticUnchokingInterval = optimisticUnchokingInterval;
  }

  public void setFileName(String fileName) {
    FileName = fileName;
  }

  public void setFileSize(int fileSize) {
    FileSize = fileSize;
  }

  public void setPieceSize(int pieceSize) {
    PieceSize = pieceSize;
  }

  public int getNumberOfPreferredNeighbors() {
    return NumberOfPreferredNeighbors;
  }

  public int getUnchokingInterval() {
    return UnchokingInterval;
  }

  public int getOptimisticUnchokingInterval() {
    return OptimisticUnchokingInterval;
  }

  public String getFileName() {
    return FileName;
  }

  public int getFileSize() {
    return FileSize;
  }

  public int getPieceSize() {
    return PieceSize;
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
}
