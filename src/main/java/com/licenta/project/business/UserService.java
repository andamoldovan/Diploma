package com.licenta.project.business;

import com.licenta.project.business.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO saveUser(UserDTO userDTO);

    void deleteUser(UserDTO userDTO);

    UserDTO login(String email, String password);
}
