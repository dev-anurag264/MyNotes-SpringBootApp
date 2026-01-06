package in.journal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	public void TestMessage() {
		emailService.sendMail("lovely.sharma1708@gmail.com", "Hi Lovely", "I am testing this using JUNIT heheheh.. its going through code i am not hard coding it lol!! i hope you are okay , i am going to teaholic hihihihi");
	}
}
