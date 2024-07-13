package com.example.jwtAuthentication.Service;


import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(String username, String password);

    String  signup(UserRequestDto userRequestDto)throws Exception;
}
