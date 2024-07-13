package com.example.jwtAuthentication.Trnasformations;

import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Models.User;

public class UserTransformation {

    public static User convertEntity(UserRequestDto userRequestDto){
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .phoneNo(userRequestDto.getPhoneNo())
                .build();
        return user;
    }
}
