package com.licenta.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.implementation.UserServiceImpl;
import com.licenta.project.business.services.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllUsers() throws Exception {

    }

    @Test
    public void saveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("anda");
        userDTO.setFirstName("anda");
        userDTO.setPassword("a");

        when(userService.saveUser(any(UserDTO.class))).thenReturn(userDTO);

//        mockMvc.perform(post("/users/saveUser")
//        .content())
    }

    @Test
    public void login() {
    }

    @Test
    public void logout() {
    }

    @Test
    public void updateReadArticles() {
    }

    @Test
    public void updateFavoriteArticles() {
    }

    @Test
    public void updateEmailSchedule() {
    }

    @Test
    public void updateArticleRatins() {
    }

    @Test
    public void getRecommendationForUser() {
    }
}