package org.doogleoss.springio.runners;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
class RunnerConfiguration {

  @Bean
  SpringRunner runner() {
    IO.println("Registering runner: springRunner");
    return new SpringRunner(new Runner("springRunner", "spring", Map.of("k1", "k2")));
  }
}
