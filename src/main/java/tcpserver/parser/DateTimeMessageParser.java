package tcpserver.parser;

import java.nio.charset.StandardCharsets;

import tcpserver.InvalidMessageException;
import tcpserver.InboundMessage.DateTimeMessage;

public class DateTimeMessageParser extends MessageParser{

	public DateTimeMessageParser(byte[] messageBytes) throws InvalidMessageException {
		super(messageBytes);
	}

	@Override
	public void parseMessage() throws InvalidMessageException {
		DateTimeMessage dateRequestMessage = new DateTimeMessage(new String(messageWithoutProtocolBytes, 
				StandardCharsets.UTF_8));
		persistEntity(dateRequestMessage);
	}

}
