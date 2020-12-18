package com.dh.daddy.scoffee.Security;

import com.dh.daddy.scoffee.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // load user
        com.dh.daddy.scoffee.Models.User u =  userService.getUser(username);

        // role

        List<SimpleGrantedAuthority> role = Arrays.asList(new SimpleGrantedAuthority( u.getPermissionLevel() ));

        // authenticated User create
        return new User( u.getUsername() ,  u.getPassword()  ,  role);
    }
}
