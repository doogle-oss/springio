package org.doogleoss.springio.dog;

import java.util.Collection;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping(value = "/dogs", version = "1.0")
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

  @GetMapping(value = "/dogs", version = "1.1")
  Collection<Map<String, Object>> dogsv11() {
    return repository.findAll().stream()
        .map(dog -> Map.of("id", dog.id(), "fullName", dog.name(), "owner", (Object) dog.owner()))
        .toList();
  }

  @GetMapping(value = "/dogs/search", produces = "application/json")
  Collection<Map<String, Object>> findByName(@RequestParam String name) {
    return repository.findByName(name).stream()
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
