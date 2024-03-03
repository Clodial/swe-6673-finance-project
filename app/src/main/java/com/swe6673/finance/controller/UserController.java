package com.swe6673.finance.controller;

import com.swe6673.finance.impl.UserServiceImpl;
import com.swe6673.finance.resource.User;
import com.swe6673.finance.resource.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User nUser = userService.createUser(user);
        return new ResponseEntity<>(nUser, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<User> getUser(@RequestBody String userId){
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User nUser = userService.updateUser(user);
        return new ResponseEntity<>(nUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        String message = userService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDTO userDTO){
        User user = userService.login(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody UserDTO userDTO){
        String message = userService.logout(userDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
