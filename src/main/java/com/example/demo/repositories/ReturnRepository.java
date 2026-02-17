package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Return;

@RepositoryRestResource(path = "devoluciones", collectionResourceRel = "devoluciones")
public interface ReturnRepository extends CrudRepository<Return, Long> {

}
