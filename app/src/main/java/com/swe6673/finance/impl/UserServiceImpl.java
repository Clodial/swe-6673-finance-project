package com.swe6673.finance.impl;

import com.swe6673.finance.repository.UserRepository;
import com.swe6673.finance.resource.User;
import com.swe6673.finance.resource.UserDTO;
import com.swe6673.finance.service.UserService;

public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public String deleteUser(String id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User login(UserDTO userDTO) {
        return null;
    }

    @Override
    public String logout(UserDTO userDTO) {
        return null;
    }
}
