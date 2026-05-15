package org.doogleoss.springio.security.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final JdbcUserDetailsManager jdbcUserDetailsManager;
  private final PasswordEncoder passwordEncoder;

  public UserService(
      JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
    this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDetails addUser(String username, String rawPassword) {
    UserDetails user =
        User.builder()
            .username(username)
            .password(passwordEncoder.encode(rawPassword))
            .roles("USER")
            .build();

    jdbcUserDetailsManager.createUser(user);
    return user;
  }
}
