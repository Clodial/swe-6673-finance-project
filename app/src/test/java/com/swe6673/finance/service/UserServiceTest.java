package com.swe6673.finance.service;

import com.swe6673.finance.impl.UserServiceImpl;
import com.swe6673.finance.repository.UserRepository;
import com.swe6673.finance.resource.User;
import com.swe6673.finance.resource.UserDTO;
import com.swe6673.finance.testbuilder.UserTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    public UserServiceImpl userService;

    @Mock
    public UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testGetUser(){
        User user = new User("124322", "matos", "cloud", "matos@cloud.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.findById(mockedUser.getUserId()));
        userService.getUser(user.getUserId());
        assertEquals(mockedUser, user);
    }

    @Test
    public void testAddNewUser(){
        User user = new User("124322", "matos", "cloud", "matos@cloud.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.save(mockedUser));
        userService.createUser(mockedUser);
        assertEquals(mockedUser, user);
    }

    @Test
    public void testUpdateUser(){
        User user = new User("124322", "matos", "cloud", "matos@cloud.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.save(mockedUser));
        userService.updateUser(mockedUser);
        assertEquals(mockedUser, user);
    }

    @Test
    public void testDeleteUser(){
        User user = new User("124322", "matos", "cloud", "matos@cloud.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();

        userService.deleteUser(mockedUser.getUserId());
        assertEquals(mockedUser, user);
    }

    @Test
    public void testLoginUser(){
        User user = new User("124322", "matos", "cloud", "matos@cloud.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(mockedUser.getEmailAddress());
        userDTO.setPassword(mockedUser.getPassword());

        userService.login(userDTO);
        assertEquals(mockedUser, user);
    }

}
