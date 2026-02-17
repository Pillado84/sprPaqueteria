package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Client;

@RepositoryRestResource(path = "clientes", collectionResourceRel = "clientes")
public interface ClientRepository extends CrudRepository<Client, Long> {

}
