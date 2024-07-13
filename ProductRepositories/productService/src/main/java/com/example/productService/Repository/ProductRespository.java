package com.example.productService.Repository;

import com.example.productService.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product, Integer> {

    List<Product> findByEmail(String email);

    List<Product> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.email = :email AND FUNCTION('YEAR', p.createdDate) = :year AND FUNCTION('MONTH', p.createdDate) = :month")
    long countProductsByEmailAndCreatedMonth(@Param("email") String email, @Param("year") int year, @Param("month") int month);


    @Query("SELECT COUNT(p) FROM Product p WHERE p.email = :email AND YEAR(p.expiryDate) = :year AND MONTH(p.expiryDate) = :month")
    int countExpiredProductsByEmailAndMonth(@Param("email") String email, @Param("year") int year, @Param("month") int month);

    @Query("SELECT p FROM Product p WHERE p.email = :email AND (p.expiryDate BETWEEN :startDate AND :endDate)")
    List<Product> findProductsByExpiryDateRange(@Param("email") String email,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.email = :email AND p.expiryDate = :expiryDate")
    int countExpiredProductsByEmailAndExpiryDate(@Param("email") String email, @Param("expiryDate") LocalDate expiryDate);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.email = :email AND p.expiryDate > :currentDate")
    long countFutureExpiringProductsByEmail(@Param("email") String email, @Param("currentDate") LocalDate currentDate);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.email = :email AND YEAR(p.expiryDate) = :year AND MONTH(p.expiryDate) = :month AND p.expiryDate < :currentDate")
    long countAlreadyExpiredProductsByEmailAndMonth(@Param("email") String email, @Param("year") int year, @Param("month") int month, @Param("currentDate") LocalDate currentDate);
}

