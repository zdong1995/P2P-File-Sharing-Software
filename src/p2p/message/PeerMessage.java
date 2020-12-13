package p2p.message;

import java.io.Serializable;

public interface PeerMessage extends Serializable{

	int getType();

	int getLength();

	int getMessageNumber();
}
