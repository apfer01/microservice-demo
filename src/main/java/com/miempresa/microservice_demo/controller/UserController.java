package com.miempresa.microservice_demo.controller;

import com.miempresa.microservice_demo.model.UserDTO;
import com.miempresa.microservice_demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Inyección automática del Service
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users → lista todos los usuarios
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    // GET /api/users/{id} → devuelve un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/users → crea un nuevo usuario
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.save(user);
    }

    // DELETE /api/users/{id} → elimina un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
