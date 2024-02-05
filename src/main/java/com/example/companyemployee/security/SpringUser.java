package com.example.companyemployee.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SpringUser extends org.springframework.security.core.userdetails.User {
    private com.example.companyemployee.entity.User user;

    public SpringUser(com.example.companyemployee.entity.User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserRole().name()));
        this.user = user;
    }

    public com.example.companyemployee.entity.User getUser() {
        return user;
    }
}
