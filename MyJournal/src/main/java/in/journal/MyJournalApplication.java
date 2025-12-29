package in.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyJournalApplication.class, args);
	}

}
