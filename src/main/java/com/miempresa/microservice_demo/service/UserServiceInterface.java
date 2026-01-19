package com.miempresa.microservice_demo.service;

import com.miempresa.microservice_demo.model.UserDTO;

import java.util.List;

public interface UserServiceInterface {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

}
