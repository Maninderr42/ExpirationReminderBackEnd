package com.example.jwtAuthentication.Dto.ResponseDto;


import com.example.jwtAuthentication.Enum.AuthStatus;

public record AuthResponseDto (String token, AuthStatus authStatus){
}
