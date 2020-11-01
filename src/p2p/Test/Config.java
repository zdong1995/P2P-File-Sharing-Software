package p2p.Test;

import p2p.peer.Process;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Config {

  public static class PeerInfo {
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
  }

  /**
   * Read PeerInfo.cfg to create the list of peer info to create PeerPrcoss
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
        res.add(new PeerInfo(Integer.parseInt(info[0]), info[1], info[2], info[3].equals("1")));
        idx++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return res;
  }

  public static Process setPeerInfo() {
    Process peerPs = new Process();
    List<PeerInfo> peersToConnect = new ArrayList<>(); //

    boolean thisPeerFound = false;
    // read PeerInfo.cfg and set the value of hasFile
    try {
      File myObj = new File("PeerInfo.cfg");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] temp = data.split(" ", 0);
        if(temp[0].equals(Integer.toString(peerPs.getPeerId()))){
          peerPs.setHostName(temp[1]);
          peerPs.setPortNum(Integer.parseInt(temp[2]));
          peerPs.setHasFile(temp[3].equals("1"));
          thisPeerFound = true;
        }
        if (!thisPeerFound) {
          peersToConnect.add(Integer.parseInt(temp[0]));
        }
      }
      // peerPs.setBitField(hasFile);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return peerPs;
  }
}
