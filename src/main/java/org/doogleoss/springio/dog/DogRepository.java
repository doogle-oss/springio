package org.doogleoss.springio.dog;

import java.util.Collection;
import org.springframework.data.repository.ListCrudRepository;

interface DogRepository extends ListCrudRepository<Dog, Integer> {

  Collection<Dog> findByName(String name);
}
