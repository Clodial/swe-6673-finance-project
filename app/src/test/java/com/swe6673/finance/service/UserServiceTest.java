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
    public void testGetUserSuccessfully(){
        User user = new User("124322", "matos", "Project", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.findById(mockedUser.getUserId()));
        userService.getUser(user.getUserId());
        assertEquals(mockedUser, user);
    }

    @Test
    public void testGetUserFail(){
        User user = new User(null, "matos", "cloud", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.findById(mockedUser.getUserId()));
        userService.getUser(user.getUserId());
        assertEquals(mockedUser, user, "Invalid user Id");
    }
    @Test
    public void testAddNewUserSuccessfully(){
        User user = new User("124322", "matos", "Project", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.save(mockedUser));
        userService.createUser(user);
        assertEquals(mockedUser, user);
    }

    @Test
    public void testAddNewUserFail(){
        User user = new User(null, null, null, "matos@project.com", null);

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.save(mockedUser));
        userService.createUser(user);
        assertEquals(mockedUser, user, "Invalid data and data type");
    }

    @Test
    public void testUpdateUserSuccessfully(){
        User user = new User("124322", "matos", "Project", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.save(mockedUser));
        userService.updateUser(user);
        assertEquals(mockedUser, user);
    }

    @Test
    public void testUpdateUserFail(){
        User user = new User("124322", "matos", "cloud", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        Mockito.when(userRepository.save(mockedUser));
        userService.updateUser(user);
        assertEquals(mockedUser, user, "Invalid user id and data type");
    }

    @Test
    public void testDeleteUserSuccessfully(){
        User user = new User("124322", "matos", "Project", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        userService.deleteUser(user.getUserId());
        assertEquals(mockedUser, user);
    }

    @Test
    public void testDeleteUserFail(){
        User user = new User(null, "matos", "cloud", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        userService.deleteUser(user.getUserId());
        assertEquals(mockedUser, user, "Invalid User Id");
    }

    @Test
    public void testLoginUserSuccessfully(){
        User user = new User("124322", "matos", "Project", "matos@project.com", "Admin_123");

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmailAddress());
        userDTO.setPassword(user.getPassword());

        userService.login(userDTO);
        assertEquals(mockedUser, user);
    }

    @Test
    public void testLoginUserFail(){
        User user = new User(null, "matos", "cloud", null, null);

        User mockedUser = UserTestBuilder.builder().userId("124322").build();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmailAddress());
        userDTO.setPassword(user.getPassword());

        userService.login(userDTO);
        assertEquals(mockedUser, user, "Invalid username and password");
    }

}
