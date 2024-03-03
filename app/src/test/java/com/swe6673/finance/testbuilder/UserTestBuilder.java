package com.swe6673.finance.testbuilder;

import com.swe6673.finance.resource.User;
import lombok.Builder;

public class UserTestBuilder {

    @Builder
    public static User user(String userId, String firstName, String lastName, String emailAddress, String password){
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setPassword(password);
        return user;
    }

    public static class UserBuilder{
        private String userId = "124322";
        private String firstName = "Matos";
        private String lastName = "Project";
        private String emailAddress = "matos@project.com";
        private String password = "Admin_123";
    }
}
