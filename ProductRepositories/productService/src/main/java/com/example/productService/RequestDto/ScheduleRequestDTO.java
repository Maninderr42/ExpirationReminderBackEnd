package com.example.productService.RequestDto;


import com.example.productService.Enum.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {

    private String title;

    private ScheduleType scheduleType;

    private String email;

    private String expiryDate;

    private String expiryTime;

    private String location;

    private String notes;
}
