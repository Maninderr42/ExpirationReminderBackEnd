package com.example.jwtAuthentication.Service.UserDetails;

import com.example.jwtAuthentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       var user =  userRepository.findUserByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("username not found"));


       return new User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }
}
