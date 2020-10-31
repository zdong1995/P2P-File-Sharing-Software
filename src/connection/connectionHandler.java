package connection;

import peer.Peer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class connectionHandler {
  private Peer host;
  private Peer neighbor;
  private ServerSocket listener;
  private ObjectOutputStream out; // stream write to the socket
  private ObjectInputStream in; // stream read from the socket

  public connectionHandler(Peer host, Peer neighbor, ServerSocket listener,
                           ObjectOutputStream out, ObjectInputStream in) {
    this.host = host;
    this.neighbor = neighbor;
    this.listener = listener;
    this.out = out;
    this.in = in;
  }
}
