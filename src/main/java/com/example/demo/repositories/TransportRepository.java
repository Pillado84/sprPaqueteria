package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Transport;

@RepositoryRestResource(path = "transportes", collectionResourceRel = "transportes")
public interface TransportRepository extends CrudRepository<Transport, Long> {
	Optional<Transport> findByMatricula(String matricula);
}