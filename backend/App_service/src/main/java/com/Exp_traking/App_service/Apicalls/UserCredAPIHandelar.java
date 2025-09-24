package com.Exp_traking.App_service.Apicalls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.Exp_traking.App_service.Pojo.UserRequest;

import reactor.core.publisher.Mono;

@Component
public class UserCredAPIHandelar {

  private final WebClient webClient;

  // Toggle or externalize via application properties; default to localhost
  private final String domain;

  public UserCredAPIHandelar(
      WebClient.Builder builder,
      @Value("${app.dbservice.base-url:http://localhost:8081}") String baseUrl) {
    this.webClient = builder
        .baseUrl(baseUrl)
        .build();
    this.domain = baseUrl; // kept for compatibility with original code
  }

  public <T> Mono<T> customApicall(String apiPath, Class<T> responseType, String method, UserRequest userRequest) {
    String url = apiPath.startsWith("http") ? apiPath : apiPath; // baseUrl already set in builder

    switch (method.toUpperCase()) {
      case "GET":
        return webClient.get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(responseType);

      case "POST":
        System.out.println("UserCredAPIHandelar POST: " + url + " " + userRequest);
        return webClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(userRequest)
            .retrieve()
            .bodyToMono(responseType);

      case "PUT":
        return webClient.put()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(userRequest)
            .retrieve()
            .bodyToMono(responseType);

      case "DELETE":
        // If the API expects a body with DELETE, use method(HttpMethod.DELETE) + body
        return webClient.method(HttpMethod.DELETE)
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(userRequest))
            .retrieve()
            .bodyToMono(responseType);

      default:
        return Mono.error(new IllegalArgumentException("Unsupported method: " + method));
    }
  }
}
