package p2p.manager;


import p2p.Controller;
import p2p.util.AsyncUtil;
import p2p.util.MessageLoggerUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;

/**
 * OptimisticUnchokeManager
 */
public class OptimisticUnchokeManager implements Runnable {
	private static OptimisticUnchokeManager instance = null;
	private Controller controller = null;
	private MessageLoggerUtil logger = null;

	private ScheduledFuture<?> task = null;

	/**
	 * get instance
	 * @param controller
	 * @return
	 */
	public static synchronized OptimisticUnchokeManager getInstance(Controller controller) {
		if (instance == null) {
			if (controller == null) {
				return null;
			}
			instance = new OptimisticUnchokeManager();
			instance.controller = controller;
			instance.logger = controller.getLogger();
		}
		return instance;
	}

	public void destroy() {
		task.cancel(true);
	}

	/**
	 * run
	 */
	public void run() {
		ArrayList<String> chokedPeers = controller.getChokedPeers();
		if (chokedPeers.size() > 0) {
			Random random = new Random();
			controller.optimisticallyUnChokePeers(chokedPeers.get(random.nextInt(chokedPeers.size())));
		}

		controller.fileDownloadComplete();
		if (controller.isDownloadComplete()) {
			logger.info("Peer [" + controller.getPeerId() + "] has downloaded the complete file.");
			controller.broadcastShutdown();
		}
	}

	/**
	 * start delay task
	 * @param startDelay
	 * @param intervalDelay
	 */
	public void start(int startDelay, int intervalDelay) {
		task = AsyncUtil.submit(this, intervalDelay);
	}

}
