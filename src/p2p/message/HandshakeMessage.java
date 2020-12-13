package p2p.message;

import p2p.common.Const;

/**
 * HandshakeMessage
 */
public class HandshakeMessage implements PeerMessage {
	private static int COUNT = 0;

	private final int messageNumber;
	private String peerId;

	public HandshakeMessage() {
		messageNumber = ++COUNT;
	}

	public void setPeerId(String peerId) {
		this.peerId = peerId;
	}

	public String getPeerId() {
		return peerId;
	}

	public int getType() {
		return Const.TYPE_HANDSHAKE_MESSAGE;
	}

	public int getLength() {
		return 0;
	}

	public int getMessageNumber() {
		return messageNumber;
	}


}
