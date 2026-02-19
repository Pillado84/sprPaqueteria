package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    // LISTAR TODOS
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    // OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // CREAR USUARIO
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    // ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User updated) {
        return ResponseEntity.ok(userService.update(id, updated));
    }

    // ELIMINAR USUARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
