package org.doogleoss.springio;

import org.doogleoss.springio.cat.CatFactsHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ConfigurationPropertiesScan
@ImportHttpServices(CatFactsHttpClient.class)
@EnableResilientMethods
@EnableMultiFactorAuthentication(
    authorities = {
      FactorGrantedAuthority.PASSWORD_AUTHORITY,
      FactorGrantedAuthority.OTT_AUTHORITY,
    })
public class SpringioApplication {

  static void main(String[] args) {
    SpringApplication.run(SpringioApplication.class, args);
  }
}
