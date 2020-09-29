package tcpserver.InboundMessage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_message")
public class UserMessage{
	
	private Integer age;
	private Integer weight;
	private Integer height;
	
	@Column(name="name_size")
	private Integer nameSize;
	private String name;

	public UserMessage(Integer age, Integer weight, Integer height, Integer nameSize, String name) {
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.nameSize = nameSize;
		this.name = name;
	}

}
