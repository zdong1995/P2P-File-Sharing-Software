package p2p.Test;

import p2p.peer.CommonConfig;
import p2p.peer.Process;
import p2p.Test.Config.PeerInfo;

import java.io.Reader;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    int peerId = Integer.parseInt(args[0]);
    String hostName = "cise.ufl.edu";
    int portNum = 6008;
    boolean hasFile = false;

    CommonConfig config = new CommonConfig();
    config.setAs("Common.cfg");
    List<PeerInfo> peerInfos = Config.readPeerInfo("PeerInfo.cfg");
    for (PeerInfo info : peerInfos) {
      if (info.getPeerId() == peerId) {
        hostName = info.getHostName();
        portNum = info.getPortNum();
        hasFile = info.isHasFile();
      }
    }
    Process peerPs = new Process(peerId, hostName, portNum, hasFile);
  }
}
