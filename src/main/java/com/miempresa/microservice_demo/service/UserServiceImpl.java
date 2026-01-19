package com.miempresa.microservice_demo.controller;

import com.miempresa.microservice_demo.model.UserDTO; // <-- usar DTO correcto
import com.miempresa.microservice_demo.entity.UserEntity;
import com.miempresa.microservice_demo.repository.UserRepository;
import com.miempresa.microservice_demo.service.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    // Inyección por constructor (recomendada)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        entity.setName(userDTO.getName());
        entity.setEmail(userDTO.getEmail());

        UserEntity saved = userRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(entity);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        entity.setName(userDTO.getName());
        entity.setEmail(userDTO.getEmail());

        return mapToDTO(userRepository.save(entity));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Método privado para mapear Entity -> DTO
    private UserDTO mapToDTO(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}