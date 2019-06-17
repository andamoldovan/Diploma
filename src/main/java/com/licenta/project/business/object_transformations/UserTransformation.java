package com.licenta.project.business.object_transformations;

import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.entities.User;

public class UserTransformation {

    public UserDTO transform(User user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(),
                user.getUserName(), user.getEmail(), user.getPassword(), user.getPreferences(),
                user.getReadArticles(), user.getFavoriteArticles(), user.getEmailSchedule());
    }

    public User transform(UserDTO user){
        return new User(user.getFirstName(), user.getLastName(),
                user.getUserName(), user.getEmail(), user.getPassword(), user.getPreferences(),
                user.getReadArticles(), user.getFavoriteArticles(), user.getEmailSchedule());
    }
}
