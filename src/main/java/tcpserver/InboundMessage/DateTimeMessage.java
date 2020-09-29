package tcpserver.InboundMessage;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="date_message")
public class DateTimeMessage{
	
	private String timezone;

	public DateTimeMessage(String timezone) {
		this.timezone = timezone;
	}

}
