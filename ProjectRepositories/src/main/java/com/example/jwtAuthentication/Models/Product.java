package com.example.jwtAuthentication.Models;

import com.example.jwtAuthentication.Enum.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Product {
    private Integer productId;

    private Integer userId;

    private String productName;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Integer quanlity;

    private Date localDate;

    private Integer Price;

    private String description;
}
