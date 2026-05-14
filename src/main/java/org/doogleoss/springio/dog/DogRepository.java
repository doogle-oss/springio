package org.doogleoss.springio.dog;

import org.springframework.data.repository.ListCrudRepository;

interface DogRepository extends ListCrudRepository<Dog, Integer> {}
