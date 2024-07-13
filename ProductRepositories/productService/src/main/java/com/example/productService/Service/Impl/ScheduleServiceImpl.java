package com.example.productService.Service.Impl;

import com.example.productService.Exception.ResourceNotFoundException;
import com.example.productService.Models.Product;
import com.example.productService.Models.Schedule;
import com.example.productService.Repository.ScheduleRespository;
import com.example.productService.RequestDto.ScheduleRequestDTO;
import com.example.productService.Service.ScheduleService;
import com.example.productService.Transformation.ScheduleTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRespository scheduleRespository;
    @Override
    public String addSchedule(ScheduleRequestDTO scheduleRequestDTO) {

        Schedule schedule= ScheduleTransformation.convertScheduleEntity(scheduleRequestDTO);

        scheduleRespository.save(schedule);

        return "Schedule Save SuccessFully......";


    }

    @Override
    public List<Schedule> getSchedule(String email) {

        List<Schedule> scheduleList=scheduleRespository.findByEmail(email);

        return scheduleList;
    }

    @Override
    public String deleteSchedule(String email, Integer scheduleId) throws ResourceNotFoundException {

        List<Schedule> scheduleList = scheduleRespository.findByEmail(email);

        boolean isScheduleDelete = false;

        for (Schedule schedule : scheduleList) {
            if (schedule.getScheduleId() == scheduleId && schedule.getEmail().equals(email)) {
                scheduleRespository.deleteById(scheduleId);
                isScheduleDelete = true;
                break;
            }
        }

        if (!isScheduleDelete) {
            throw new ResourceNotFoundException("Schedule with ID " + scheduleId + " not found " + email);
        }

        return "Schedule Delete SuccesFully";
    }
}
