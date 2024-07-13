package com.example.productService.Transformation;

import com.example.productService.Models.Product;
import com.example.productService.RequestDto.EditProductRequestDTO;
import com.example.productService.RequestDto.ProductRequestDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProductTransformation {

    public static Product convertProductEntity(ProductRequestDto productRequestDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(productRequestDto.getLocalDate(), formatter);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate2 = LocalDate.parse(productRequestDto.getCreatedDated(), formatter2);


        Product product=Product.builder()
                .productName(productRequestDto.getProductName())
                .email(productRequestDto.getEmail())
                .type(productRequestDto.getType())
                .createdDate(localDate2)
                .expiryDate(localDate)
                .quantity(productRequestDto.getQuantity())
                .Price(productRequestDto.getPrice())
                .description(productRequestDto.getDescription())
                .build();

        return product;
    }

}
