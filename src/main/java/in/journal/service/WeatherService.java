package in.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.journal.api.response.WeatherResponse;

@Service
public class WeatherService {
	
	@Autowired
	private AppCache appCache;

//	private static final String api_key = "";
//	private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";	
    private final RestTemplate restTemplate;
    

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Autowired
    private RedisService redisService;
    
    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
               
        	String urlTemplate = appCache.get("WEATHER_API");
        String apiKey = appCache.get("WEATHER_API_KEY");
	        
	        String finalUrl = urlTemplate
	                .replace("<API_KEY>", apiKey)
	                .replace("<CITY>", city);
        	
        	ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.POST, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }
    
//    
//	public WeatherResponse getWeather(String city) {
//		String cacheKey = "WEATHER::" + city.toLowerCase();
//		WeatherResponse response=redisService.get(cacheKey, WeatherResponse.class);
//		if(response!=null) {
//			System.out.println("CACHE HIT for " + city);
//			return response;
//		}else {
//			
//			String urlTemplate = appCache.get("WEATHER_API");
//	        String apiKey = appCache.get("WEATHER_API_KEY");
//	        
//	        String finalUrl = urlTemplate
//	                .replace("<API_KEY>", apiKey)
//	                .replace("<CITY>", city);
//	        ResponseEntity<WeatherResponse> resp = restTemplate.getForEntity(finalUrl, WeatherResponse.class);
//	        
//	        WeatherResponse resppp = resp.getBody();
//	        
//	        if(resppp!=null) {
//	        	redisService.set(cacheKey,resppp, 3000l );
//	        	System.out.println("Stored in Redis again");
//	        }
//	        return resppp;
//		}
//		
//		
//        
////		String finalapi = API.replace("CITY", city).replace("API_KEY", api_key);
////		ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET, null,
////				WeatherResponse.class);
////		WeatherResponse body = response.getBody();
////		return body;
//		
//	}
}
