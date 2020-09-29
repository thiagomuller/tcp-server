package tcpserver.InboundMessage;

import java.nio.charset.StandardCharsets;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TEXT_MESSAGE")
public class TextMessage{

	private String messageText;

	public TextMessage(String messageText) {
		this.messageText = messageText;
	}

}
