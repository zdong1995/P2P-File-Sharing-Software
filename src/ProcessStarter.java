import p2p.Controller;
import p2p.message.meta.PeerInfo;
import p2p.util.OutputDisplayUtil;
import p2p.util.PeerInfoPropertyUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * process start
 */
public class ProcessStarter {

	/**
	 * main method
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception{
		ProcessStarter starter = new  ProcessStarter();
		starter.remoteStartProcesses();
	}

	/**
	 * start local process
	 */
	public void localStartProcesses(){
		Controller controller = Controller.getInstance("1");
		controller.startController();
	}

	/**
	 * start remote process
	 * @throws Exception
	 */
	public void remoteStartProcesses() throws Exception{

		PeerInfoPropertyUtil fileReader = PeerInfoPropertyUtil.getInstance();
		String path = System.getProperty("user.dir");

		HashMap<String, PeerInfo> peerMap = fileReader.getPeerInfoMap();


		for (String peer : peerMap.keySet()) {
			PeerInfo peerInfo = peerMap.get(peer);
			String runCommand = "java PeerProcess";
			String peerId = peerInfo.getPeerId();

			// linked to remote server
			Process serverProcess = Runtime.getRuntime().exec("ssh " + peerInfo.getAddress() + " cd " + path + " ;" +runCommand+" "+peerId);

			OutputDisplayUtil outputDisplayUtil = new OutputDisplayUtil(peer, new BufferedReader(new InputStreamReader(serverProcess.getInputStream()))  );
			new Thread(outputDisplayUtil).start();

			OutputDisplayUtil errorDisplayer = new OutputDisplayUtil(peer, new BufferedReader(new InputStreamReader(serverProcess.getErrorStream()))  );
			new Thread(errorDisplayer ).start();

			Thread.sleep(5000);

			System.out.println("Started Process "+peerInfo.getPeerId() +" on "+peerInfo.getAddress()+" and port number : "+peerInfo.getPort());
		}
	}
}
