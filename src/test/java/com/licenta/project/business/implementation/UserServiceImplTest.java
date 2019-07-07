package com.licenta.project.business.implementation;

import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.services.UserService;
import com.licenta.project.entities.User;
import com.licenta.project.repositories.mongo.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(Stream.of(new User("Anda", "Moldovan", "anda", "anda", "a", new ArrayList<>(), 0, new ArrayList<>(), "12.00", new HashMap<>()),
                new User("Dana", "Moldovan", "dana", "dana", "d", new ArrayList<>(), 0, new ArrayList<>(), "12.00", new HashMap<>()))
            .collect(Collectors.toList()));

        List<User> list = userRepository.findAll();
        Assert.assertEquals(2, list.size());
        Mockito.verify(userRepository).findAll();
    }

    @Test
    public void saveUser(){
        User user = new User("Anda", "Moldovan", "anda", "anda", "a", new ArrayList<>(), 0, new ArrayList<>(), "12.00", new HashMap<>());
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Assert.assertEquals(user, userRepository.save(user));
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void login(){
        User user = new User("firstname", "lastname", "anda", "email", "pass", new ArrayList<>(), 0, new ArrayList<>(), "12.00", new HashMap<>());

        Mockito.when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);

        Assert.assertEquals(user, userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()));
        Mockito.verify(userRepository).findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

}