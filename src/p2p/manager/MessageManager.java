package p2p.manager;

import p2p.common.Const;
import p2p.message.HandshakeMessage;
import p2p.message.Peer2PeerMessage;
import p2p.message.PeerMessage;

import java.nio.ByteBuffer;

/**
 * MessageManager
 */
public class MessageManager {
	private static volatile MessageManager instance;

	/**
	 * get instance
	 * @return
	 */
	public static MessageManager getInstance() {
		if (instance == null) {
			instance = new MessageManager();
		}
		return instance;
	}

	public byte[] geHandshakeMessage(byte[] rawData) {
		String head = Const.HANDSHAKE_HEADER_STRING;
		char[] array = head.toCharArray();
		byte[] messageByte = new byte[32];
		for (int i = 0; i < 18; i++) {
			messageByte[i] = (byte) array[i];
		}
		for (int i = 18; i < 31; i++) {
			messageByte[i] = (byte) 0;
		}
		messageByte[31] = rawData[3];

		return messageByte;
	}

	public byte[] getRequestMessage(int pieceIndex) {
		return null;
	}

	public byte[] getChokeMessage() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.putInt(Const.SIZE_OF_EMPTY_MESSAGE);
		buffer.put(Const.TYPE_CHOKE_MESSAGE);
		return buffer.array();

	}

	public byte[] getUnchokeMessage() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.putInt(Const.SIZE_OF_EMPTY_MESSAGE);
		buffer.put(Const.TYPE_UNCHOKE_MESSAGE);
		return buffer.array();
	}

	public byte[] getInterestedMessage() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.putInt(Const.SIZE_OF_EMPTY_MESSAGE);
		buffer.put(Const.TYPE_INTERESTED_MESSAGE);
		return buffer.array();
	}

	public byte[] getNotInterestedMessage() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.putInt(Const.SIZE_OF_EMPTY_MESSAGE);
		buffer.put(Const.TYPE_NOT_INTERESTED_MESSAGE);
		return buffer.array();

	}

	public byte[] getHaveMessage(byte[] payLoad) {
		ByteBuffer buffer = ByteBuffer.allocate(9);
		buffer.putInt(5);
		buffer.put(Const.TYPE_HAVE_MESSAGE);
		buffer.put(payLoad);
		return buffer.array();

	}

	public byte[] getBitFieldMessage(byte[] byteData) {
		int payloadSize = byteData.length;
		ByteBuffer buffer = ByteBuffer.allocate(payloadSize + 5);
		buffer.putInt(payloadSize + 1);
		buffer.put(Const.TYPE_BITFIELD_MESSAGE);
		buffer.put(byteData);

		return buffer.array();
	}

	public byte[] getRequestMessage(byte[] payLoad) {
		ByteBuffer buffer = ByteBuffer.allocate(9);
		buffer.putInt(5);
		buffer.put(Const.TYPE_REQUEST_MESSAGE);
		buffer.put(payLoad);
		return buffer.array();
	}

	public HandshakeMessage parseHandShakeMessage(byte[] rawData) {
		return null;
	}

	public Peer2PeerMessage parsePeer2PeerMessage(byte[] rawData) {
		return null;
	}

	/**
	 * parse bytes to message
	 * @param rawData
	 * @return
	 */
	public PeerMessage parse(byte[] rawData) {
		if (rawData == null || rawData.length < 5) {
			return null;
		}

		byte type = rawData[4];
		switch (type) {
			case Const.TYPE_CHOKE_MESSAGE: {
				Peer2PeerMessage message = Peer2PeerMessage.create();
				message.setMessageType(Const.TYPE_CHOKE_MESSAGE);
				message.setLength(1);
				message.setData(null);
				return message;
			}
			case Const.TYPE_UNCHOKE_MESSAGE: {
				Peer2PeerMessage message = Peer2PeerMessage.create();
				message.setMessageType(Const.TYPE_UNCHOKE_MESSAGE);
				message.setLength(1);
				message.setData(null);
				return message;
			}
			case Const.TYPE_INTERESTED_MESSAGE: {
				Peer2PeerMessage message = Peer2PeerMessage.create();
				message.setMessageType(Const.TYPE_INTERESTED_MESSAGE);
				message.setLength(1);
				message.setData(null);
				return message;
			}
			case Const.TYPE_NOT_INTERESTED_MESSAGE: {
				Peer2PeerMessage message = Peer2PeerMessage.create();
				message.setMessageType(Const.TYPE_NOT_INTERESTED_MESSAGE);
				message.setLength(1);
				message.setData(null);
				return message;
			}
			case Const.TYPE_HAVE_MESSAGE: {
				Peer2PeerMessage message = Peer2PeerMessage.create();
				message.setLength(5);
				message.setLength(Const.TYPE_HAVE_MESSAGE);
				message.setIndex(rawData[8]);
				break;
			}
			case Const.TYPE_REQUEST_MESSAGE: {
				Peer2PeerMessage message = Peer2PeerMessage.create();
				message.setLength(5);
				message.setLength(Const.TYPE_REQUEST_MESSAGE);
				message.setIndex(rawData[8]);
				break;
			}
		}
		return null;
	}
}
