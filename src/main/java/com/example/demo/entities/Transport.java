package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok
@Data // Getters y setters, equals y hashcode, toString
@AllArgsConstructor // Constructor de todos los parámetros
@NoArgsConstructor // Constructor vacío
@Builder // Patrón Builder

//JPA
@Entity
@Table(name = "transportes")
public class Transport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "La matrícula no puede estar vacía")
	@Size(max = 8, message = "La matrícula no puede tener más de 8 caracteres")
	@Column(unique = true, nullable = false)
	private String matricula;
	
	@NotNull(message = "El tipo de vehículo es obligatorio")
	@Enumerated(EnumType.STRING)
	private TipoVehiculo tipo;
	
	@NotNull(message = "La capacidad es obligatoria")
	private Integer capacidadKg;
	
	@NotBlank(message = "El nombre del conductor no puede estar vacío")
	private String conductor;
	
	@NotBlank(message = "El teléfono no puede estar vacío")
	@Size(max = 15)
	private String telefono;
	
	@NotNull(message = "El estado es obligatorio")
	@Enumerated(EnumType.STRING)
	private EstadoTransporte estado;

	// ENUMS
	public enum TipoVehiculo {
		FURGONETA, CAMION, MOTO, TRAILER
	}

	public enum EstadoTransporte {
		DISPONIBLE, EN_RUTA, MANTENIMIENTO, FUERA_DE_SERVICIO
	}
}
