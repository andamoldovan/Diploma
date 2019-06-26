package com.licenta.project.business.implementation;

import com.licenta.project.business.UserService;
import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.object_transformations.UserTransformation;
import com.licenta.project.business.recommandation_system.RecommandationService;
import com.licenta.project.entities.User;
import com.licenta.project.repositories.mongo.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserTransformation userTransformation;
    private final RecommandationService recommandationService;

    public UserServiceImpl(UserRepository userRepository, RecommandationService recommandationService) {
        this.userRepository = userRepository;
        this.recommandationService = recommandationService;
        this.userTransformation = new UserTransformation();
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList =  userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        for(User u : userList){
            result.add(userTransformation.transform(u));
        }
        return result;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = encodeUserPassword(userTransformation.transform(userDTO));
        User existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(existingUser == null){
            return userTransformation.transform(userRepository.save(user));
        }
        return new UserDTO();
    }

    @Override
    public void deleteUser(UserDTO userDTO) {
        User user = userTransformation.transform(userDTO);
        userRepository.delete(user);
    }

    @Override
    public UserDTO login(String email, String password) {
        String encodedPassword = encodePassword(password);
        User user = userRepository.findByEmailAndPassword(email, encodedPassword);

        if(user == null) return userTransformation.transform(new User());
        return userTransformation.transform(user);
    }

    @Override
    public UserDTO updateReadArticles(UserDTO userDTO) {
        User user = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(user != null){
            user.setReadArticles(userDTO.getReadArticles());

            return userTransformation.transform(userRepository.save(user));
        }
        return null;
    }

    @Override
    public UserDTO updateFavoriteArticles(UserDTO userDTO) {
        User user = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(user != null){
            user.setFavoriteArticles(userDTO.getFavoriteArticles());

            return userTransformation.transform(userRepository.save(user));
        }
        return null;
    }

    @Override
    public UserDTO updateEmailScheduler(UserDTO userDTO) {
        User user = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(user != null){
            user.setEmailSchedule(userDTO.getEmailSchedule());

            return userTransformation.transform(userRepository.save(user));
        }
        return null;
    }

    @Override
    public List<UserDTO> getUsersWithEmailSchedules(String time) {
        List<User> list = userRepository.findUsersByEmailSchedule(time);
        List<UserDTO> result = new ArrayList<>();

        for(User user : list){
            result.add(userTransformation.transform(user));
        }
        return result;
    }

    @Override
    public UserDTO updateRatings(UserDTO userDTO) {
        User user = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(user != null){
            user.setArticleRatings(userDTO.getArticleRatings());
            return userTransformation.transform(userRepository.save(user));
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getArticlePrediction(UserDTO userDTO) {
        List<User> allUsers = userRepository.findAll();
        List<UserDTO> users = new ArrayList<>();
        for(User u : allUsers){
            UserDTO newUserDTO = userTransformation.transform(u);
            users.add(newUserDTO);
        }
        return recommandationService.getRecommandationForUser((ArrayList<UserDTO>) users, userDTO);
    }

    //encoding done with SHA-256
    private String encodePassword(String password) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodeHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i< encodeHash.length; i++){
                String hex = Integer.toHexString(0xff & encodeHash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
       return null;
    }

    private User encodeUserPassword(User user){
        user.setPassword(encodePassword(user.getPassword()));
        return user;
    }
}
