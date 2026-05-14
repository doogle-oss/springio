package org.doogleoss.springio.dog;

import java.util.Collection;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
class DogsController {

  private final DogRepository repository;

  DogsController(DogRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/dogs")
  Collection<Map<String, Object>> dogs() {
    return repository.findAll().stream()
        .map(
            dog ->
                Map.of(
                    "id",
                    dog.id(),
                    "fullName",
                    dog.name(),
                    "owner",
                    dog.owner(),
                    "description",
                    (Object) dog.description()))
        .toList();
  }
}
