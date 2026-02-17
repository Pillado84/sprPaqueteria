package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Sending.TipoEnvio;
import com.example.demo.entities.Sending.EstadoEnvio;
import com.example.demo.entities.Transport.EstadoTransporte;
import com.example.demo.entities.Transport.TipoVehiculo;
import com.example.demo.entities.User.Rol;
import com.example.demo.entities.Return.EstadoDevolucion;

@RestController
public class EnumController {
	@GetMapping("/api/v1/enums/tipos-vehiculo")
	public TipoVehiculo[] getTiposVehiculo() {
		return TipoVehiculo.values();
	}

	@GetMapping("/api/v1/enums/estados-transporte")
	public EstadoTransporte[] getEstadosTransporte() {
		return EstadoTransporte.values();
	}
	
	@GetMapping("/api/v1/enums/tipos-envios")
	public TipoEnvio[] getTiposEnvios() {
		return TipoEnvio.values();
	}

	@GetMapping("/api/v1/enums/estados-envios")
	public EstadoEnvio[] getEstadosEnvios() {
		return EstadoEnvio.values();
	}
	
	@GetMapping("/api/v1/enums/roles-usuarios")
	public Rol[] getRolesUsuarios() {
		return Rol.values();
	}
	
	@GetMapping("/api/v1/enums/estados-devoluciones")
	public EstadoDevolucion[] getEstadosDevoluciones() {
		return EstadoDevolucion.values();
	}
}
