package com.example.productService.Service;

import com.example.productService.Exception.ResourceNotFoundException;
import com.example.productService.Models.Schedule;
import com.example.productService.RequestDto.ScheduleRequestDTO;

import java.util.List;

public interface ScheduleService {


    String addSchedule(ScheduleRequestDTO scheduleRequestDTO);

    List<Schedule> getSchedule(String email);

    String deleteSchedule(String email, Integer scheduleId) throws ResourceNotFoundException;
}
