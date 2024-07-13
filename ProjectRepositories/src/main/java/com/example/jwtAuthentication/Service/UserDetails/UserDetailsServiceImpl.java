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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       var user =  userRepository.findUserByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("username not found"));


       return new User(user.getEmail(),user.getPassword(),user.getAuthorities());
    }
}
