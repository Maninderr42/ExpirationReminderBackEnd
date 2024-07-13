package com.example.jwtAuthentication.Dto.RequsetDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailUpdate {

   private String email;

   private String firstName;

  private   String lastName;

   private String bio;

    private String country;

    private String state;

    private String StreetAddress;

    private String businessName;

    private String businessAddress;

    private String businessType;
}
