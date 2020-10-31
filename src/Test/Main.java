package Test;

import peer.PeerProcess;
import peer.Peer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static PeerProcess readConfig(int PeerId) {
    PeerProcess peerPs = new PeerProcess(PeerId);
    ArrayList<Integer> peersAhead = new ArrayList<>(); // TODO: -> Peer List
    // read Common.cfg
    // [peer ID] [host name] [listening port] [has file or not]
    try {
      File myObj = new File("Common.cfg");
      Scanner myReader = new Scanner(myObj);
      int i=0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        //System.out.println(data);
        String[] temp = data.split(" ", 0);
        switch(i){
          case 0:
            peerPs.setNumberOfPreferredNeighbors(Integer.parseInt(temp[1]));
            break;
          case 1:
            peerPs.setUnchokingInterval(Integer.parseInt(temp[1]));
            break;
          case 2:
            peerPs.setOptimisticUnchokingInterval(Integer.parseInt(temp[1]));
            break;
          case 3:
            peerPs.setFileName(temp[1]);
            break;
          case 4:
            peerPs.setFileSize(Integer.parseInt(temp[1]));
            break;
          case 5:
            peerPs.setPieceSize(Integer.parseInt(temp[1]));
            break;
          default:
            System.out.println("error with the format of Common.cfg");
        }
        i++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

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
        if(!thisPeerFound){
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

  public static void main(String[] args) {
    PeerProcess peerPs = readConfig(Integer.parseInt(args[0]));
    peerPs.startListener();
    peerPs.run();
    peerPs.printInfo();
  }
}
