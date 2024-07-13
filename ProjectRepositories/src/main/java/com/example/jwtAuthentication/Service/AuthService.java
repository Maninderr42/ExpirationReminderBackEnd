package com.example.jwtAuthentication.Service;


import com.example.jwtAuthentication.Dto.RequsetDto.UserConnectionUpdateDTO;
import com.example.jwtAuthentication.Dto.RequsetDto.UserDetailUpdate;
import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Exceptions.UserNotFoundException;
import com.example.jwtAuthentication.Models.User;

public interface AuthService {
    String login(String email, String password);

    String  signup(UserRequestDto userRequestDto)throws Exception;

     User getUser(String email);

    String userNameUpdate(UserDetailUpdate userDetailUpdate) throws UserNotFoundException;

    String userConnectionUpdate(UserConnectionUpdateDTO userConnectionUpdateDTO) throws UserNotFoundException;
}
