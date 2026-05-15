package org.doogleoss.springio.cat;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
class CatFactsClient {
  private final RestClient http;

  CatFactsClient(RestClient.Builder http) {
    this.http = http.build();
  }

  CatFacts getCatFacts() {
    return http.get()
        .uri("https://catfacts.net/api")
        .retrieve()
        .body(CatFacts.class);
  }
}
