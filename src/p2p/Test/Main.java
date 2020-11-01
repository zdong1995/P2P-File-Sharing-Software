package p2p.Test;

import p2p.peer.Process;

public class Main {

  public static void main(String[] args) {
    Process peerPs = Config.peerInfo();
    peerPs.startListener();
    peerPs.run();
    peerPs.printInfo();
  }
}
