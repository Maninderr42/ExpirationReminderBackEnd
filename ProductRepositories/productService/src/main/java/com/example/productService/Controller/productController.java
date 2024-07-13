package com.example.productService.Controller;


import com.example.productService.Exception.ResourceNotFoundException;
import com.example.productService.Models.Product;
import com.example.productService.RequestDto.EditProductRequestDTO;
import com.example.productService.RequestDto.MonthsExpiredRequestDTO;
import com.example.productService.RequestDto.ProductRequestDto;
import com.example.productService.RequestDto.YearExpiredRequestDTO;
import com.example.productService.Service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000") // Use * for all origins

public class productController{

    @Autowired
    private ProductService productService;

    @PostMapping("/AddProduct")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) throws Exception{


        try {
            String res = productService.addProduct(productRequestDto);
            return new ResponseEntity(res,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/alertProduct/{email}")
    public ResponseEntity<List<Product>> alertProduct(@PathVariable String email, @RequestBody Map<String, String> request){

            String date = request.get("date");
            List<Product> res = productService.alertProduct(email, date);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

    }
    @GetMapping("/getProduct/{email}")
    public ResponseEntity getProduct (@PathVariable String email ){
        try {
            List<Product> res= productService.getProduct(email);
            return new ResponseEntity(res,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity deleteProduct(@PathParam("email") String email, @PathParam("productId") Integer productId) {
        try {
            String res=productService.deleteProduct(email,productId);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editProduct")
    public ResponseEntity editProduct(@RequestBody EditProductRequestDTO editProductRequestDTO){
        try {
            String res=productService.editProduct(editProductRequestDTO);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/yearExpired")
    public ResponseEntity<?> getExpiredByYear(@RequestParam String email, @RequestParam int year) {
        // Your logic here

        try {
            Map<Integer,Integer>  res=productService.yearExpired(email, year);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/ExpiredByMonth")
    public ResponseEntity ExpiredByMonth(@RequestBody MonthsExpiredRequestDTO monthsExpiredRequestDTO){

        try {
            Map<String,Integer>  res=productService.expiredByMonth(monthsExpiredRequestDTO.getEmail(), monthsExpiredRequestDTO.getLocalDate());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/HomeBar")
    public ResponseEntity HomeBar(@RequestBody  MonthsExpiredRequestDTO monthsExpiredRequestDTO){
        try {
            Map<String,Long>  res=productService.HomeBar(monthsExpiredRequestDTO.getEmail(), monthsExpiredRequestDTO.getLocalDate());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/getGraphCretedDate")
    public ResponseEntity getGraphCreatedDate(@RequestBody YearExpiredRequestDTO yearExpiredRequestDTO) {

        try {
            List<Map<Integer, Long>>  res=productService.getGraphCreatedDate(yearExpiredRequestDTO.getEmail(), yearExpiredRequestDTO.getYear());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}
