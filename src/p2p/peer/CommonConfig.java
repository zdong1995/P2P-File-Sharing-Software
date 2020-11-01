package p2p.peer;

import p2p.Test.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommonConfig {

  private int numberOfPreferredNeighbors; // TODO
  private int unchokingInterval;
  private int optimisticUnchokingInterval;
  private String fileName;
  private int fileSize;
  private int pieceSize;

  public CommonConfig() {
  }

  /**
   * Read Common.cfg to setup common config for PeerProcess to initialize
   *
   * @param path the common config file path.
   */
  public void setAs(String path) {
    try {
      BufferedReader in = new BufferedReader(new FileReader(path));
      while (in.readLine() != null) {
        String[] cfg = in.readLine().split(" "); // [property, value]
        if (cfg[0].equals("NumberOfPreferredNeighbors")) {
          this.numberOfPreferredNeighbors = Integer.parseInt(cfg[1]) + 1;
        } else if (cfg[0].equals("UnchokingInterval")) {
          this.unchokingInterval = Integer.parseInt(cfg[1]);
        } else if (cfg[0].equals("OptimisticUnchokingInterval")) {
          this.optimisticUnchokingInterval = Integer.parseInt(cfg[1]);
        } else if (cfg[0].equals("FileName")) {
          this.fileName = cfg[1];
        } else if (cfg[0].equals("FileSize")) {
          this.fileSize = Integer.parseInt(cfg[1]);
        } else if (cfg[0].equals("PieceSize")) {
          this.pieceSize = Integer.parseInt(cfg[1]);
        }
      }
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
