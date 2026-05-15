package org.doogleoss.springio.cat;

import org.springframework.web.service.annotation.GetExchange;

public interface CatFactsHttpClient {

  @GetExchange("https://catfacts.net/api")
  CatFacts getCatFacts();
}
