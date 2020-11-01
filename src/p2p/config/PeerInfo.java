package p2p.config;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class PeerInfo {
  /**
   * Wrapper class of Peer Information specified in "PeerInfo.cfg"
   */
  private int peerId;
  private String hostName;
  private int portNum;
  private boolean hasFile;

  public PeerInfo(int peerId, String hostName, int portNum, boolean hasFile) {
    this.peerId = peerId;
    this.hostName = hostName;
    this.portNum = portNum;
    this.hasFile = hasFile;
  }

  /**
   * Read PeerInfo.cfg to create the list of peer info to create PeerProcess
   * @param path
   * @return
   */
  public static List<PeerInfo> readPeerInfo(String path) {
    List<PeerInfo> res = new ArrayList<>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(path));
      int idx = 0;
      while (in.readLine() != null) {
        String line = in.readLine().trim();
        if (line.length() <= 0) { // skip empty line
          continue;
        }
        String[] info = line.split(" ");
        if (info.length != 4) {
          throw new ParseException(line, idx);
        }
        res.add(new PeerInfo(Integer.parseInt(info[0]), info[1],
            Integer.parseInt(info[2]), info[3].equals("1")));
        idx++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return res;
  }

  public int getPeerId() {
    return peerId;
  }

  public String getHostName() {
    return hostName;
  }

  public int getPortNum() {
    return portNum;
  }

  public boolean isHasFile() {
    return hasFile;
  }

  @Override
  public String toString() {
    return "PeerInfo{" +
        "peerId=" + peerId +
        ", hostName='" + hostName + '\'' +
        ", portNum=" + portNum +
        ", hasFile=" + hasFile +
        '}';
  }
}
