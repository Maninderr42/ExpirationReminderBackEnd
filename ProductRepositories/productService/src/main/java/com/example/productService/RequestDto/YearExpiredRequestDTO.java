package com.example.productService.RequestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class YearExpiredRequestDTO {

    private String email;
    private Integer year;

}
