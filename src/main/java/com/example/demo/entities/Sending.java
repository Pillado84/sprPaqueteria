package com.example.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//JPA
@Entity
@Table(name = "envios")
public class Sending {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String codigoSeguimiento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoEnvio tipo;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoEnvio estado;

	// Fechas del flujo
	@PastOrPresent
	private LocalDateTime fechaCreacion;
	
	@PastOrPresent
	private LocalDateTime fechaRecogida;
	
	@PastOrPresent
	private LocalDateTime fechaEntradaAlmacen;
	
	@PastOrPresent
	private LocalDateTime fechaSalidaAlmacen;
	
	@PastOrPresent
	private LocalDateTime fechaEntrega;
	
	@PastOrPresent
	private LocalDateTime fechaCancelacion;

	// Relación con transporte (puede cambiar)
	@ManyToOne
	@JoinColumn(name = "transporte_id")
	private Transport transporte;

	// Si el remitente es un cliente registrado
	@ManyToOne
	@JoinColumn(name = "cliente_remitente_id")
	private Client clienteRemitente;
	
	// Si el remitente NO es cliente
	private String nombreRemitente;
	
	private String direccionRemitente;

	// Datos del destinatario
	@NotBlank
	private String nombreDestinatario;

	@NotBlank
	private String direccionDestinatario;

	// ENUMS
	public enum TipoEnvio {
		NORMAL, URGENTE
	}

	public enum EstadoEnvio {
		INICIALIZADO, RECOGIDO, EN_ALMACEN, EN_REPARTO, FINALIZADO, CANCELADO
	}
}
