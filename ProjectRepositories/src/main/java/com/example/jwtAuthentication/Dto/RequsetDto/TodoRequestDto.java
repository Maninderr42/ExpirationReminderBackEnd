package com.example.jwtAuthentication.Dto.RequsetDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDto {

    private String title;

    private String content;

    private String email;
}
