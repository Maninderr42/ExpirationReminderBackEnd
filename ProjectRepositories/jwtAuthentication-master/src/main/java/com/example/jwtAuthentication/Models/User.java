package com.example.jwtAuthentication.Models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

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

    private String username;
    private String email;
    private String password;
    private String age;
    private String gender;

    List<GrantedAuthority> authorities;
}
