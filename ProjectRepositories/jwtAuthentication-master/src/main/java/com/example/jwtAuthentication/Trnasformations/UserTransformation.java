package com.example.jwtAuthentication.Trnasformations;

import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Models.User;

public class UserTransformation {

    public static User convertEntity(UserRequestDto userRequestDto){
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .age(userRequestDto.getAge())
                .gender(userRequestDto.getGender())
                .build();
        return user;
    }
}
