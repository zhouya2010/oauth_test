package com.sidu.service.impl;

import com.sidu.dao.UserMapper;
import com.sidu.domain.MyUserDetails;
import com.sidu.domain.User;
import com.sidu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2017/1/21.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

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
        return new MyUserDetails(mapper.selectByUserName(s));
    }
}
