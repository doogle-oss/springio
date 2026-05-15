package org.doogleoss.springio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.dialect.JdbcPostgresDialect;

@Configuration
class JdbcConfiguration {

  @Bean
  JdbcPostgresDialect jdbcPostgresDialect() {
    return JdbcPostgresDialect.INSTANCE;
  }
}
