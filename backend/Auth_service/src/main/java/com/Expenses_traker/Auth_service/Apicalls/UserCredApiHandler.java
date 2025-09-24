package com.Expenses_traker.Auth_service.Apicalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserCredApiHandler {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081";

    @Autowired
    public UserCredApiHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T callApi(String path, HttpMethod method, Object body, Class<T> responseType) {
        String url = baseUrl + path;
        HttpEntity<?> entity = new HttpEntity<>(body);

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, method, entity, responseType);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            // Can map specific codes (404 → NotFoundException, etc.)
            throw new RuntimeException("API call failed: " + e.getStatusCode() + 
                                       " Body: " + e.getResponseBodyAsString(), e);
        }
    }
}
