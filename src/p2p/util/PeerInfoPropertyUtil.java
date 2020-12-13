package p2p.util;

import p2p.common.Const;
import p2p.message.meta.PeerInfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * PeerInfoPropertyUtil.
 *
 * @author sagar
 */
public class PeerInfoPropertyUtil {

	/**
	 * PeerInfo.cfg data
	 */
	private LinkedHashMap<String, PeerInfo> peerInfoMap = null;

	/**
	 * instance
	 */
	private static PeerInfoPropertyUtil instance = null;

	/**
	 * getInstance
	 * @return
	 */
	public static PeerInfoPropertyUtil getInstance() {
		if (instance == null) {
			instance = new PeerInfoPropertyUtil();
			instance.init();
		}
		return instance;
	}

	/**
	 * init and parse data in PeerInfo.cfg file
	 * @return
	 */
	public boolean init() {
		peerInfoMap = new LinkedHashMap<>();
		try {
			BufferedReader configFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(Const.PEER_INFO_FILE)));
			String line = null;
			while ((line = configFileReader.readLine()) != null) {
				line = line.trim();
				String[] tokens = line.split(" "); //File is separated by space character
				PeerInfo peerInfoInstance = new PeerInfo();
				peerInfoInstance.setPeerId(tokens[0]);
				peerInfoInstance.setAddress(tokens[1]);
				peerInfoInstance.setPort(Integer.parseInt(tokens[2]));
				peerInfoInstance.setFileExist(tokens[3].equals("1"));

				peerInfoMap.put(tokens[0], peerInfoInstance);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * get parsed data
	 * @return
	 */
	public HashMap<String, PeerInfo> getPeerInfoMap() {
		return peerInfoMap;
	}
}
