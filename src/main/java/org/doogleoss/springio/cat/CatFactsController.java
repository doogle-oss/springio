package org.doogleoss.springio.cat;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CatFactsController {

  private final CatFactsClient client;
  private final CatFactsHttpClient httpClient;
  private final AtomicInteger counter = new AtomicInteger(0);
  CatFactsController(CatFactsClient client, CatFactsHttpClient httpClient) {
    this.client = client;
    this.httpClient = httpClient;
  }

  @GetMapping(value = "/cats", version = "1.0")
  CatFacts catFactsv10() {
    return this.client.getCatFacts();
  }

  @ConcurrencyLimit(10) //Semaphore to restrict how many concurrent call can go through
  @Retryable(maxRetries = 5, includes = IllegalStateException.class)
  @GetMapping(value = "/cats", version = "1.1")
  CatFacts catFactsv11() {
    if(counter.incrementAndGet() < 5)
    {
      IO.println("Simulating Temp Error....");
      throw new IllegalStateException("Temp Error Simulation");
    }
    IO.println("Phew temp Error resolved....");
    return this.httpClient.getCatFacts();
  }
}
