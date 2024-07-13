package com.example.productService.Service;

import com.example.productService.Exception.ResourceNotFoundException;
import com.example.productService.Models.Product;
import com.example.productService.RequestDto.EditProductRequestDTO;
import com.example.productService.RequestDto.ProductRequestDto;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    String addProduct(ProductRequestDto productRequestDto);

    List<Product> getProduct(String email);

    String deleteProduct(String email, Integer productId) throws ResourceNotFoundException;

    List<Product> alertProduct(String email ,String date);

    String editProduct(EditProductRequestDTO editProductRequestDTO) throws FileNotFoundException;

    Map<Integer, Integer> yearExpired(String email, int year);

    Map<String, Integer> expiredByMonth(String email, String localDate);

    Map<String, Long> HomeBar(String email,String date);

    List<Map<Integer, Long>> getGraphCreatedDate(String email, int year);
}
