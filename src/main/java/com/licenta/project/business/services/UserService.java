package com.licenta.project.business.services;

import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO saveUser(UserDTO userDTO);

    void deleteUser(UserDTO userDTO);

    UserDTO login(String email, String password);

    UserDTO updateReadArticles(UserDTO userDTO);

    UserDTO updateFavoriteArticles(UserDTO userDTO);

    UserDTO updateEmailScheduler(UserDTO userDTO);

    List<UserDTO> getUsersWithEmailSchedules(String time);

    UserDTO updateRatings(UserDTO userDTO);

    List<ArticleDTO> getArticlePrediction(UserDTO userDTO);
}
