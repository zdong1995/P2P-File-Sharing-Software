package p2p.peer;

import p2p.config.CommonConfig;
import p2p.config.PeerInfo;

import java.util.*;

public class PeerProcess {
  /**
   * Start the PeerProcess with id from command line arguments
   * By `java PeerProcess 1001`
   *
   * @param args Peer ID number
   */
  public static void main(String[] args) {
    int peerId = Integer.parseInt(args[0]);
    CommonConfig config = new CommonConfig();
    config.setAs("Common.cfg");

    List<PeerInfo> peerInfos = PeerInfo.readPeerInfo("PeerInfo.cfg");

    // for (PeerInfo p : peerInfos) {
    //  System.out.println(p);
    // }

    List<PeerInfo> peersToConnect = new ArrayList<>();
    Process peerPs = new Process(peerId, config);
    System.out.println("Create a peer with peerId = " + peerId);
    for (PeerInfo info : peerInfos) {
      if (info.getPeerId() == peerId) {
        peerPs.setHostName(info.getHostName());
        peerPs.setPortNum(info.getPortNum());
        peerPs.setHasFile(info.isHasFile());
      } else {
        peersToConnect.add(info);
      }
    }

    // peerPs.initialize(); TODO: will create new thread
    for (PeerInfo peerToCon : peersToConnect) { // TODO: duplicate conn
      peerPs.makeConnection(peerToCon.getPeerId());
    }
    System.out.println(peerPs);
    peerPs.run();
  }
}