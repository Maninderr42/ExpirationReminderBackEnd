package com.example.productService.Controller;

import com.example.productService.RequestDto.SettingDTO;
import com.example.productService.Service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @PostMapping("/add")
    public ResponseEntity addSetting(@RequestBody SettingDTO settingDTO) {

        try {
            String response = settingService.AddSetting(settingDTO);
            return new ResponseEntity(response, org.springframework.http.HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }
}
