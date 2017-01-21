package com.sidu.service.impl;

import com.sidu.domain.User;
import com.sidu.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by dell on 2017/1/21.
 */
public class UserServiceImpl implements UserService {
    @Override
    public User loadCurrentUserJsonDto() {
        return null;
    }

    @Override
    public User loadUserOverviewDto(User overviewDto) {
        return null;
    }

    @Override
    public boolean isExistedUsername(String username) {
        return false;
    }

    @Override
    public String saveUser(User formDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
