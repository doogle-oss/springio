package org.doogleoss.springio.registrar;

import java.util.List;
import org.doogleoss.springio.runners.Runner;
import org.doogleoss.springio.runners.SpringRunner;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

class AppBeanRegistrar implements BeanRegistrar {

  @Override
  public void register(@NonNull BeanRegistry registry, @NonNull Environment env) {
    // 1. Bind properties from the environment to a List of POJOs
    List<Runner> runners =
        Binder.get(env)
            .bind("app.runners", Bindable.listOf(Runner.class))
            .orElse(List.of()); // Fallback to an empty list if not defined

    for (Runner runner : runners) {
      IO.println("Registering runner: " + runner.name());
      registry.registerBean(
          runner.name(), SpringRunner.class, spec -> spec.supplier(_ -> new SpringRunner(runner)));
    }
  }
}
