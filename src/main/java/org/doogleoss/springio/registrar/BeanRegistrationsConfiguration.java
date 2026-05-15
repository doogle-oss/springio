package org.doogleoss.springio.registrar;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// BeanRegistrationsConfiguration.java
@Configuration
@Import(AppBeanRegistrar.class)   // registers MyBeanRegistrar as a registrar
public class BeanRegistrationsConfiguration {
}