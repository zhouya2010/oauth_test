package com.sidu.service;

import com.sidu.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Shengzhao Li
 */
public interface UserService extends UserDetailsService {

    User loadCurrentUserJsonDto();

    User loadUserOverviewDto(User overviewDto);

    boolean isExistedUsername(String username);

    String saveUser(User formDto);
}