package com.example.demo.controllers;

import com.example.demo.security.auth.*;
import com.example.demo.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {

		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		// ✔ Ya no hace falta llamar a userDetailsService
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		String token = jwtUtil.generateToken(userDetails.getUsername());

		return ResponseEntity.ok(new JwtResponse(token));
	}
}
