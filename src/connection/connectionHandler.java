package connection;

import peer.Peer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class connectionHandler extends Thread {

  private Peer host;
  private Peer neighbor;
  private ObjectOutputStream out; // stream write to the socket
  private ObjectInputStream in; // stream read from the socket

  public connectionHandler(Peer host, Peer neighbor, ObjectOutputStream out, ObjectInputStream in) {
    this.host = host;
    this.neighbor = neighbor;
    this.out = out;
    this.in = in;
  }

  public void sendMessage(String msg, Socket requestSocket) {
    try {
      out = new ObjectOutputStream(requestSocket.getOutputStream());
      out.writeObject(msg);
      out.flush();
      System.out.println("Send message: " + msg );
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  public void receiveMessage() { // TODO

  }
}
