package org.doogleoss.springio.runners;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class SpringRunner implements ApplicationRunner {
  private final Runner runner;

  public SpringRunner(Runner runner) {
    this.runner = runner;
  }

  @Override
  public void run(@NonNull ApplicationArguments args) throws Exception {
    IO.println(runner);
  }
}
