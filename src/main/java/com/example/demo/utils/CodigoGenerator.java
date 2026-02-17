package com.example.demo.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Sending.TipoEnvio;
import com.example.demo.repositories.SendingRepository;

@Component
public class CodigoGenerator {
	private static final String ALFANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final SecureRandom random = new SecureRandom();
	private final SendingRepository envioRepository;

	public CodigoGenerator(SendingRepository envioRepository) {
		this.envioRepository = envioRepository;
	}

	public String generarCodigo(TipoEnvio tipo) {
		String codigo;
		do {
			codigo = tipo.name() + "-" + generarRandom(6);
		} while (envioRepository.existsByCodigoSeguimiento(codigo));
		return codigo;
	}

	private String generarRandom(int longitud) {
		StringBuilder sb = new StringBuilder(longitud);
		for (int i = 0; i < longitud; i++) {
			sb.append(ALFANUM.charAt(random.nextInt(ALFANUM.length())));
		}
		return sb.toString();
	}
}
