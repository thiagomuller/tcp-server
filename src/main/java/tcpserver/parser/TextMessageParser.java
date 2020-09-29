package tcpserver.parser;

import java.nio.charset.StandardCharsets;

import tcpserver.InvalidMessageException;
import tcpserver.InboundMessage.TextMessage;

public class TextMessageParser extends MessageParser{

	public TextMessageParser(byte[] messageBytes) throws InvalidMessageException {
		super(messageBytes);
	}

	@Override
	public void parseMessage() throws InvalidMessageException {
		TextMessage message = new TextMessage(new String(messageWithoutProtocolBytes, 
				StandardCharsets.UTF_8));
		persistEntity(message);
	}
}
