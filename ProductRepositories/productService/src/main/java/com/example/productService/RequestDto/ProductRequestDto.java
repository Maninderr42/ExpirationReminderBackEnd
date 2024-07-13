package com.example.productService.RequestDto;

import com.example.productService.Enum.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String productName;

    private String email;

    private Type type;

    private Integer quantity;

    private String createdDated;

    private String localDate;

    private Integer price;

    private String description;
}
