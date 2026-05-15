package org.doogleoss.springio.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
class SecurityConfiguration {

  @Bean
  JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  // No longer needed - use Customizer instead from sb4/sf7
  //  @Bean
  //  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  //    return http.csrf(AbstractHttpConfigurer::disable)
  //        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
  //        .httpBasic(Customizer.withDefaults())
  //        .build();
  //  }

  // http://localhost:8080/webauthn/register -> managed by os and the url to register is below.
  @Bean
  Customizer<HttpSecurity> httpSecurityCustomizer() {
    return http ->
        //        http.csrf(AbstractHttpConfigurer::disable)
        http.webAuthn(
                a ->
                    a.allowedOrigins("http://localhost:8080")
                        .rpId("localhost")
                        .rpName("doogle-oss"))
            .oneTimeTokenLogin(
                ott ->
                    ott.tokenGenerationSuccessHandler(
                        (request, response, oneTimeToken) -> {
                          // Generate a one-time token and include it in the response header
                          response.getWriter().println("you've got console mail....");
                          response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                          IO.println(
                              "Please go to http://localhost:8080/login/ott?token="
                                  + oneTimeToken.getTokenValue());
                        }));
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
