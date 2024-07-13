package com.example.productService.Controller;


import com.example.productService.Models.Schedule;
import com.example.productService.RequestDto.ScheduleRequestDTO;
import com.example.productService.Service.ScheduleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("schedule")
public class ScheduleController {


    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/addSchedule")
    public ResponseEntity addSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {

        try {
            String res = scheduleService.addSchedule(scheduleRequestDTO);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getSchedule/{email}")
    public ResponseEntity getSchedule(@PathVariable String email) {
        try {
            List<Schedule> res = scheduleService.getSchedule(email);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteSchedule")
    public ResponseEntity deleteSchedule(@PathParam("email") String email, @PathParam("scheduleId") Integer scheduleId) {

        try {
            String res = scheduleService.deleteSchedule(email, scheduleId);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}