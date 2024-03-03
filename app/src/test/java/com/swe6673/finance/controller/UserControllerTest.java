package com.swe6673.finance.controller;

import com.swe6673.finance.config.AppConfig;
import com.swe6673.finance.resource.User;
import com.swe6673.finance.resource.UserDTO;
import com.swe6673.finance.testbuilder.UserTestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AppConfig.class)
public class UserControllerTest {

    private static final String API_URLPATH = "/api/user";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateNewUser() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        this.mockMvc.perform(post(API_URLPATH, mockedUser)).andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateUser() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        this.mockMvc.perform(put(API_URLPATH, mockedUser)).andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUser() throws Exception{
        String userId = "124322";
        this.mockMvc.perform(delete(API_URLPATH + "/delete/{id}", userId)).andExpect(status().isOk());
    }

    @Test
    public void shouldGetUser() throws Exception{
        String userId = "124322";
        this.mockMvc.perform(get(API_URLPATH, userId)).andExpect(status().isOk());
    }

    @Test
    public void shouldLogUserIn() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(mockedUser.getEmailAddress());
        userDTO.setPassword(mockedUser.getPassword());
        this.mockMvc.perform(get(API_URLPATH + "/login", userDTO)).andExpect(status().isOk());
    }

    @Test
    public void shouldLogUserOut() throws Exception{
        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(mockedUser.getEmailAddress());
        userDTO.setPassword(mockedUser.getPassword());
        this.mockMvc.perform(post(API_URLPATH + "/logout", userDTO)).andExpect(status().isOk());
    }

}
