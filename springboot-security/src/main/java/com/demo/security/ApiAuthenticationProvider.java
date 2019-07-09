package com.demo.security;

import com.demo.dto.DemoUserDTO;
import com.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // todo: user atomservice to auth
        DemoUserDTO demoUserDTO = loginService.getUserByEmail(username);
        if (demoUserDTO == null) {
            throw new BadCredentialsException("username or password incorrect");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(password, demoUserDTO.getPassword())) {
            throw new BadCredentialsException("username or password incorrect");
        }

        // todo: 确认禁用状态是多少
        if (demoUserDTO.getStatus() != 1) {
            throw new DisabledException("user is disabled");
        }

        // todo: 权限处理
        List<GrantedAuthority> authorities = new ArrayList<>();

        Authentication auth = new ApiAuthenticationToken(demoUserDTO, password, authorities);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiAuthenticationToken.class);
    }
}
