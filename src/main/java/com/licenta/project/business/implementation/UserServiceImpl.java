package com.licenta.project.business.implementation;

import com.licenta.project.business.UserService;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.object_transformations.UserTransformation;
import com.licenta.project.entities.User;
import com.licenta.project.repositories.UserRepository;
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

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return userTransformation.transform(userRepository.save(user));
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
