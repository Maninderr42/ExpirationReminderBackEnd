package com.example.productService.RequestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthsExpiredRequestDTO {

    private String email;

    private String localDate;
}
