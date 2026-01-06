package in.journal.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.journal.config.App_Config;
import in.journal.repository.ConfigApp;
import jakarta.annotation.PostConstruct;

@Component
public class AppCache {

	@Autowired
	private ConfigApp configs;
	
	 private final Map<String, String> cache = new ConcurrentHashMap<>();
	 
	 @PostConstruct
	 public void init() {

		 System.out.println("Loading configs into cache...");
		 List<App_Config> conf = configs.findAll();
		 for (App_Config config : conf) {
	            cache.put(config.getKey(), config.getValue());
	        }
		 
		 System.out.println(" App cache loaded: " + cache.keySet());
	 }
	 
	   public String get(String key) {
	        return cache.get(key);
	    }
}
