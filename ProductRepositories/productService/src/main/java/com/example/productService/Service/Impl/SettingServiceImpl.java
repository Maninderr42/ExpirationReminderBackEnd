package com.example.productService.Service.Impl;

import com.example.productService.Models.Setting;
import com.example.productService.Repository.SettingRepository;
import com.example.productService.RequestDto.SettingDTO;
import com.example.productService.Service.SettingService;
import com.example.productService.Transformation.ProductTransformation;
import com.example.productService.Transformation.SettingTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingServiceImpl implements SettingService {


    @Autowired
    private SettingRepository settingRepository;
    @Override
    public String AddSetting(SettingDTO settingDTO) throws Exception {
//        Optional<Setting> optional=settingRepository.findByEmail(settingDTO.getEmail());
//
//        if(!optional.isEmpty()){
//            throw new Exception("Email already created");
//        }

        Setting setting= SettingTransformation.SettingConvertEntity(settingDTO);

        settingRepository.save(setting);

        return "Succesfully...........";
    }
}
