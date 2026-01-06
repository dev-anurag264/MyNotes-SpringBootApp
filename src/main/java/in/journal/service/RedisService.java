package in.journal.service;

import java.util.concurrent.TimeUnit;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import tools.jackson.databind.ObjectMapper;

@Service
public class RedisService {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Lets see if it comes here?"+o);
            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            System.out.println("Error "+ e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
            System.out.println("Lets see what is theis"+jsonValue);
        } catch (Exception e) {
        	System.out.println("Error::: "+ e);
        }
    }
}
