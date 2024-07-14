package com.javahunter.UserApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahunter.UserApp.entity.User;
import com.javahunter.UserApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setup(){
        user = new User();
        user.setName("Raghav raj");
        user.setPassword("12345");
    }

    @Test
    void testHello() throws Exception {
        mockMvc.perform(get("/user/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hey...You made it...!!!... Congratulations"));
    }

    @Test
    void testSaveUser() throws Exception {
        Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Raghav raj")))
                .andExpect(jsonPath("$.password", is("12345")));
    }
}
