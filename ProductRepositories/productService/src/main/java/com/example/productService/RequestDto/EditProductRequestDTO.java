package com.example.productService.RequestDto;

import com.example.productService.Enum.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditProductRequestDTO {

    private Integer productId;

    private String productName;

    private String email;

    private Type type;

    private Integer quantity;

    private String expiryDate;

    private String description;

    private Integer price;

}
