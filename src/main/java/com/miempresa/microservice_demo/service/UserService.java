package com.miempresa.microservice_demo.service;

import com.miempresa.microservice_demo.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<UserDTO> users = new ArrayList<>();

    public List<UserDTO> findAll() {
        return users;
    }

    public Optional<UserDTO> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public UserDTO save(UserDTO user) {
        users.add(user);
        return user;
    }

    public boolean delete(Long id) {
        return users.removeIf(u -> u.getId().equals(id));
    }
}
