package com.example.jwtAuthentication.Models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer  id;

    private String email;

    private String firstName;

    private String lastName;

    private String bio;

    private String password;

    private String phoneNo;

    private String country;

    private String state;

    private String StreetAddress;

    private String businessName;

    private String businessAddress;

    private String businessType;

    private String alternateEmail;

    private String alternatePhoneNo;

    List<GrantedAuthority> authorities;

    @Transient
    List<Product> productList;
    @Transient
    List<Schedule> scheduleList;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
     List<Todo> todoList=new ArrayList<>();
}
