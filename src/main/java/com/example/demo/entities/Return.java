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
@Table(name = "devoluciones")
public class Return {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Relación con el envío original
	@NotNull
	@ManyToOne
	@JoinColumn(name = "envio_id", nullable = false)
	private Sending envioOriginal;

	// Código de seguimiento del envío original (para consulta rápida)
	@NotBlank
	@Column(nullable = false)
	private String codigoSeguimientoEnvio;

	// Estado de la devolución
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoDevolucion estado;

	// Fechas del flujo
	@PastOrPresent
	private LocalDateTime fechaInicio;

	@PastOrPresent
	private LocalDateTime fechaRecogida;

	@PastOrPresent
	private LocalDateTime fechaFinalizacion;

	// Datos del remitente de la devolución (antes era destinatario del envío)
	@NotBlank
	private String nombreRemitente;

	@NotBlank
	private String direccionRemitente;

	// Datos del destinatario de la devolución (antes era remitente del envío)
	@NotBlank
	private String nombreDestinatario;

	@NotBlank
	private String direccionDestinatario;

	// Transporte asignado (opcional)
	@ManyToOne
	@JoinColumn(name = "transporte_id")
	private Transport transporte;

	public enum EstadoDevolucion {
		INICIADO, EN_REPARTO, FINALIZADO
	}
}
