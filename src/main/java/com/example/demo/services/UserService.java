package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User create(User user) {
        // Encriptar contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, User updated) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setNombre(updated.getNombre());
                    user.setApellidos(updated.getApellidos());
                    user.setEmail(updated.getEmail());
                    user.setRol(updated.getRol());
                    user.setActivo(updated.getActivo());

                    // Si viene una nueva contraseña, la encriptamos
                    if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
                        user.setPassword(passwordEncoder.encode(updated.getPassword()));
                    }

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }
}
