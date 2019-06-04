package com.licenta.project.controllers;

import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.implementation.UserServiceImpl;
import com.licenta.project.entities.User;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public UserDTO login(@RequestParam("email") String email, @RequestParam("password") String password){
        return userService.login(email, password);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean logout(@RequestBody UserDTO userDTO){
        String filepath = "D:/Users/andam/Documents/MEGA/_Diploma/server-logs/user-article-files/current_article_list_" + userDTO.getId();
        File file = new File(filepath);
        if(file.exists()){
            file.delete();
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value= "/update/readArticles", method = RequestMethod.POST, headers= "Accept=application/json")
    @ResponseBody
    public UserDTO updateReadArticles(@RequestBody UserDTO userDTO){
        return userService.updateReadArticles(userDTO);
    }

    @RequestMapping(value = "/update/favoriteArticle", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public UserDTO updateFavoriteArticles(@RequestBody UserDTO userDTO){
        return userService.updateFavoriteArticles(userDTO);
    }

}
