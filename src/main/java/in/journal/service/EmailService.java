package in.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String to, String subject, String body) {
		
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setSubject(subject);
			mail.setText(body);
			mail.setFrom("anuragcse23@gmail.com");
			mailSender.send(mail);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}
