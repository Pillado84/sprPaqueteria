package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.User;

@RepositoryRestResource(path = "usuarios", collectionResourceRel = "usuarios")
public interface UserRepository extends CrudRepository<User, Long> {

}
