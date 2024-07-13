package com.example.productService.Models;


import com.example.productService.Enum.ScheduleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    private String scheduleTitle;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    private String email;

    private LocalDate expiryDate;

    private LocalTime expiryTime;

    private String address;

    private String notes;





}
