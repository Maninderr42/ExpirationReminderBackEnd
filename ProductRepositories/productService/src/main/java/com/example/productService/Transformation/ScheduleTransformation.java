package com.example.productService.Transformation;

import com.example.productService.Models.Schedule;
import com.example.productService.RequestDto.ScheduleRequestDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ScheduleTransformation {

    public static Schedule convertScheduleEntity(ScheduleRequestDTO scheduleRequestDTO) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(scheduleRequestDTO.getExpiryDate(), dateFormatter);

        // Adjusting the time formatter to match "h:mm a" for AM/PM format
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime localTime;
        try {
            localTime = LocalTime.parse(scheduleRequestDTO.getExpiryTime(), timeFormatter);
        } catch (DateTimeParseException e) {
            // Handle parsing exception, e.g., log error and set default time
            localTime = LocalTime.MIDNIGHT;
        }

        Schedule schedule = Schedule.builder()
                .scheduleTitle(scheduleRequestDTO.getTitle())
                .email(scheduleRequestDTO.getEmail())
                .scheduleType(scheduleRequestDTO.getScheduleType())
                .address(scheduleRequestDTO.getLocation())
                .expiryDate(localDate)
                .expiryTime(localTime)
                .notes(scheduleRequestDTO.getNotes())
                .build();

        return schedule;
    }
}
