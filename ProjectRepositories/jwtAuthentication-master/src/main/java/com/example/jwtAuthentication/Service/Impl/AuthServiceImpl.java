package com.example.jwtAuthentication.Service.Impl;

import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Exceptions.UserAlreadyPresentException;
import com.example.jwtAuthentication.Models.User;
import com.example.jwtAuthentication.Repository.UserRepository;
import com.example.jwtAuthentication.Service.AuthService;
import com.example.jwtAuthentication.Trnasformations.UserTransformation;
import com.example.jwtAuthentication.Utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;



    public String login(String username, String password)  {


        var authToken= new UsernamePasswordAuthenticationToken(username,password);

       var authenticate=authenticationManager.authenticate(authToken);

       //it give user details
//        ((UserDetails)(authenticate.getPrincipal())).getUsername();

        return JwtUtils.GenerateToken(((UserDetails)(authenticate.getPrincipal())).getUsername());


    }


    @Override
    public String signup(UserRequestDto userRequestDto) throws Exception {

        //building user entity
        User user = UserTransformation.convertEntity(userRequestDto);


        //check weather user already exist or not

        Optional<User> optionalUser = userRepository.findUserByUsername(user.getUsername());

        if(optionalUser.isPresent()){

            throw new UserAlreadyPresentException("this user name is already present");

        }

        //create authorities
       var authorities =  new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //encript password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(authorities);


        userRepository.save(user);

        //generate token

        return JwtUtils.GenerateToken(user.getUsername());






    }
}
