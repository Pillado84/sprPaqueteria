package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Sending;

@RepositoryRestResource(path = "envios", collectionResourceRel = "envios")
public interface SendingRepository extends CrudRepository<Sending, Long> {
	boolean existsByCodigoSeguimiento(String codigoSeguimiento);
}
