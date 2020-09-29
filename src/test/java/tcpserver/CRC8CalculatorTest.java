package tcpserver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRC8CalculatorTest {
	private final String testMessage = "h";
	private CRC8Calculator calculator = new CRC8Calculator();
	
	@Test
	public void givenTestMessageAndDefinedDiviserShouldReturn0x25() {
		try {
			byte[] textBytes = testMessage.getBytes();
			calculator.update(textBytes, 0, textBytes.length);
			long crc = calculator.getValue();
			assertTrue(crc ==  0x1F);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
