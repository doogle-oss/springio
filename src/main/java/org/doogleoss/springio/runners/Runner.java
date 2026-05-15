package org.doogleoss.springio.runners;

import java.util.Map;

public record Runner(String name, String type, Map<String, String> properties) {}
