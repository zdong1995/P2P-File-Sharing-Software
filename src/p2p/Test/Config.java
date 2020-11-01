package p2p.Test;

import p2p.peer.Process;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Config {

  public static Process peerInfo() {
    Process peerPs = new Process();
    ArrayList<Integer> peersAhead = new ArrayList<>(); // TODO: -> Peer List

    boolean thisPeerFound = false;
    // read PeerInfo.cfg and set the value of hasFile
    try {
      File myObj = new File("PeerInfo.cfg");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] temp = data.split(" ", 0);
        if (temp[0].equals(Integer.toString(peerPs.getPeerId()))) {
          peerPs.setHostName(temp[1]);
          peerPs.setPortNum(Integer.parseInt(temp[2]));
          peerPs.setHasFile(temp[3].equals("1"));
          thisPeerFound = true;
        }
        if (!thisPeerFound) {
          peersAhead.add(Integer.parseInt(temp[0]));
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
