package com.example.Wordle.controllers;

import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.models.User;
import com.example.Wordle.services.Auth.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.beans.factory.annotation.Autowired;
import
        org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import
        org.springframework.boot.autoconfigure.security.servlet
                .SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc
        .AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet
        .AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest
        .WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result
        .MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AuthController.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureTestDatabase
class AuthControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private AuthService authService;
//
//    @Test
//    public void whenValidInput_thenCreateUser() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("Username","Password","email@gmai.com");
//        mvc.perform(post("auth/register").contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userRegistrationDTO)));
//    }
}