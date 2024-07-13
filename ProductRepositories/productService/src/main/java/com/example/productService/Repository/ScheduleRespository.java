package com.example.productService.Repository;

import com.example.productService.Enum.ScheduleType;
import com.example.productService.Models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRespository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByEmail(String email);

    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.email = :email AND YEAR(s.expiryDate) = :year AND MONTH(s.expiryDate) = :month")
    int countExpiredSchedulesByEmailAndMonth(@Param("email") String email, @Param("year") int year, @Param("month") int month);

    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.email = :email AND YEAR(s.expiryDate) = :year AND MONTH(s.expiryDate) = :month AND s.scheduleType = :scheduleType")
    int countExpiredSchedulesByEmailAndByMonth(@Param("email") String email, @Param("year") int year, @Param("month") int month, @Param("scheduleType") ScheduleType scheduleType);

    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.email = :email AND s.expiryDate = :expiryDate")
    long countExpiredSchedulesByEmailAndExpiryDate(@Param("email") String email, @Param("expiryDate") LocalDate expiryDate);

    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.email = :email AND s.expiryDate > :currentDate")
    long countFutureExpiringSchedulesByEmail(@Param("email") String email, @Param("currentDate") LocalDate currentDate);

    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.email = :email AND YEAR(s.expiryDate) = :year AND MONTH(s.expiryDate) = :month AND s.expiryDate < :currentDate")
    long countAlreadyExpiredSchedulesByEmailAndMonth(@Param("email") String email, @Param("year") int year, @Param("month") int month, @Param("currentDate") LocalDate currentDate);
}


//    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.email = :email AND s.endDate BETWEEN :startOfMonth AND :endOfMonth")
//    int countByEndDateBetween(@Param("email") String email, @Param("startOfMonth") LocalDate startOfMonth, @Param("endOfMonth") LocalDate endOfMonth);
//
//    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.email = :email AND YEAR(s.endDate) = :year AND MONTH(s.endDate) = :month")
//    int countExpiredSchedulesByEmailAndMonth(@Param("email") String email, @Param("year") int year, @Param("month") int month);

