package org.doogleoss.springio.dog;

import org.springframework.data.annotation.Id;

record Dog(@Id int id, String name, String owner, String description) {}
