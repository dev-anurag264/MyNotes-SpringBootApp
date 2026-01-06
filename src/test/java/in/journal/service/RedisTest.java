package in.journal.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RedisTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void sendMail() {
	 redisTemplate.opsForValue().set("email","gmail@gmail.com");
	 Object email = redisTemplate.opsForValue().get("email");
	 
	}
}
