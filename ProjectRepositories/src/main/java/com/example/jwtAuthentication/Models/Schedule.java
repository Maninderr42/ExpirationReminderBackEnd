package com.example.jwtAuthentication.Models;

import com.example.jwtAuthentication.Enum.ScheduleType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {



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
