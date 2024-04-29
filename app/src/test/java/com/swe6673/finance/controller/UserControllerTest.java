package com.swe6673.finance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe6673.finance.config.AppConfig;
import com.swe6673.finance.resource.User;
import com.swe6673.finance.resource.UserDTO;
import com.swe6673.finance.service.UserService;
import com.swe6673.finance.testbuilder.UserTestBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = AppConfig.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String API_URLPATH = "/api/user";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;


    @Test
    public void shouldCreateNewUserSuccessfully() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        String requestBody = objectMapper.writeValueAsString(mockedUser);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(mockedUser);
        this.mockMvc.perform(post(API_URLPATH).contentType("application/json").content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFailCreateNewUser() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId(null).build();
        String requestBody = objectMapper.writeValueAsString(mockedUser);
        this.mockMvc.perform(post(API_URLPATH).contentType("application/json").content(requestBody))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void shouldUpdateUserSuccessfully() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        String requestBody = objectMapper.writeValueAsString(mockedUser);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(mockedUser);

        this.mockMvc.perform(put(API_URLPATH).contentType("application/json").content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailUpdateUser() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId(null).build();
        String requestBody = objectMapper.writeValueAsString(mockedUser);
        this.mockMvc.perform(put(API_URLPATH).contentType("application/json").content(requestBody))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void shouldDeleteUserSuccessfully() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        String requestBody = objectMapper.writeValueAsString(mockedUser);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(mockedUser);
        this.mockMvc.perform(delete(API_URLPATH + "/delete/" + mockedUser.getUserId())).andExpect(status().isOk());
    }

    @Test
    public void shouldFailDeleteUser() throws Exception{
        String userId = "null";
        this.mockMvc.perform(delete(API_URLPATH + "/delete/" + userId)).andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetUserSuccessfully() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        String id = mockedUser.getUserId();
        Mockito.when(userService.getUser(id)).thenReturn(mockedUser);
        this.mockMvc.perform(get(API_URLPATH+"/"+id)).andExpect(status().isOk());
    }

    @Test
    public void shouldFailGetUser() throws Exception{
        String userId = "null";
        this.mockMvc.perform(get(API_URLPATH, userId)).andExpect(status().isNotFound());
    }
    @Test
    public void shouldLogUserInSuccessfully() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(mockedUser.getEmailAddress());
        userDTO.setPassword(mockedUser.getPassword());
        Mockito.when(userService.login(userDTO)).thenReturn(mockedUser);
        this.mockMvc.perform(get(API_URLPATH + "/login", userDTO)).andExpect(status().isOk());
    }

    @Test
    public void shouldFailLogUserIn() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(null);
        userDTO.setPassword(null);
        this.mockMvc.perform(get(API_URLPATH + "/login", userDTO)).andExpect(status().is5xxServerError());
    }
    @Test
    public void shouldLogUserOutSuccessfully() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(mockedUser.getEmailAddress());
        userDTO.setPassword(mockedUser.getPassword());
        Mockito.when(userService.logout(userDTO)).thenReturn("successfully log out");
        this.mockMvc.perform(post(API_URLPATH + "/logout", userDTO)).andExpect(status().isOk());
    }

    @Test
    public void shouldFailLogUserOut() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(null);
        userDTO.setPassword(null);
        this.mockMvc.perform(post(API_URLPATH + "/logout", userDTO)).andExpect(status().is5xxServerError());
    }
}
