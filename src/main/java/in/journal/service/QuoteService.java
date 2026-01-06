package in.journal.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.journal.api.response.QuoteResponse;

@Service
public class QuoteService {
	
	private final RestTemplate restTemplate;
    private final String quoteUrl;
    private final String apiKey;

    public QuoteService(
            RestTemplate restTemplate,
            @Value("${api.ninjas.url}") String quoteUrl,
            @Value("${api.ninjas.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.quoteUrl = quoteUrl;
        this.apiKey = apiKey;
    }

	
	public QuoteResponse getQuote() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        
        ResponseEntity<QuoteResponse[]> response=restTemplate.exchange(
        		quoteUrl + "?categories=success,wisdom",
        		HttpMethod.GET,
        		entity,
        		QuoteResponse[].class
        		);
        if (response.getStatusCode().is2xxSuccessful()
                && response.getBody() != null
                && response.getBody().length > 0) {

            return response.getBody()[0]; 
        }
    
        return null;
	}
	
}
