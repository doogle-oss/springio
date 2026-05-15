package org.doogleoss.springio.security.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/users", produces = "application/json", consumes = "application/json")
  public ResponseEntity<UserDetails> addUser(@RequestBody UserRequest request) {
    var userDetails = userService.addUser(request.username, request.password);
    return ResponseEntity.ok(userDetails);
  }

  public record UserRequest(String username, String password) {}
}
