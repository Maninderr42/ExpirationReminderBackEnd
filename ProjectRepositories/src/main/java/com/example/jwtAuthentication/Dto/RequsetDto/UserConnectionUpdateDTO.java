package com.example.jwtAuthentication.Dto.RequsetDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConnectionUpdateDTO {

    private  String email;

    private String alternatephoneNo;

    private String alternatePhone;


}
