package tcpserver.parser;

import java.nio.charset.StandardCharsets;

import tcpserver.InvalidMessageException;
import tcpserver.InboundMessage.UserMessage;

public class UserMessageParser extends MessageParser{

	public UserMessageParser(byte[] messageBytes) throws InvalidMessageException {
		super(messageBytes);
	}

	@Override
	public void parseMessage() throws InvalidMessageException {
		byte[] username = getUserName(messageWithoutProtocolBytes);
		Integer age = Integer.valueOf(messageWithoutProtocolBytes[0]);
		Integer weight = Integer.valueOf(messageWithoutProtocolBytes[1]);
		Integer height = Integer.valueOf(messageWithoutProtocolBytes[2]);
		Integer nameSize = Integer.valueOf(messageWithoutProtocolBytes[3]);
		UserMessage user = new UserMessage(age, weight, height,nameSize, 
				new String(username, StandardCharsets.UTF_8));
		persistEntity(user);
	}
	
	public byte[] getUserName(byte[] message) {
		byte[] result = new byte[message.length - 4];
		for(int i = 0; i < message.length; i++) {
			if(i > 3) {
				result[i - 4] = message[i];
			}
		}
		return result;
	}

}
