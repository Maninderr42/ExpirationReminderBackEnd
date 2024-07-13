package com.example.productService.Models;
import com.example.productService.Enum.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String email;

    private String productName;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Integer quantity;

    private LocalDate createdDate;

    private LocalDate expiryDate; // Changed to LocalDate

    private Integer Price;

    private String description;
}
