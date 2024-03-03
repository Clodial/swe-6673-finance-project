package com.swe6673.finance.service;

import com.swe6673.finance.resource.User;
import com.swe6673.finance.resource.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User createUser(User user);
    public User updateUser(User user);
    public User getUser(String userId);
    public String deleteUser(String id);
    public User login(UserDTO userDTO);
    public String logout(UserDTO userDTO);
}
