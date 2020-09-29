package tcpserver.parser;

import tcpserver.HibernateInfra;
import tcpserver.InvalidMessageException;

public abstract class MessageParser {
	
	protected byte[] messageWithoutProtocolBytes;
	protected HibernateInfra db;
	
	public MessageParser(byte[] messageBytes) throws InvalidMessageException{
		if(messageBytes ==  null) {
			throw new InvalidMessageException("Invalid message");
		}
		this.messageWithoutProtocolBytes = getMessageWithoutProcolBytes(messageBytes);
		this.db = new HibernateInfra();
	}
	
	public abstract void parseMessage() throws InvalidMessageException;
	
	public void persistEntity(Object object) {
		db.getSession().save(object);
	}
	
	private byte[] getMessageWithoutProcolBytes(byte[] messageBytes) throws InvalidMessageException{
		if(messageBytes.length <= 5) {
			throw new InvalidMessageException("Invalid message, it contains least than 5 bytes, "
					+ "meaning that most likely it doesn't have some of the necessary attributes");
		}
		byte[] messageWithoutProtocolBytes = new byte[messageBytes.length - 3];
		for(int i  = 0; i < messageBytes.length - 2; i++) {
			if(i > 1) {
				messageWithoutProtocolBytes[i] = messageBytes[i];
			}
		}
		return messageWithoutProtocolBytes;
	}
}
