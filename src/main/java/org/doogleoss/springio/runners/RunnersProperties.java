package org.doogleoss.springio.runners;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record RunnersProperties(List<Runner> runners) {}
