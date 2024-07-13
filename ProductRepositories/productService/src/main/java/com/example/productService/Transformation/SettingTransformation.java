package com.example.productService.Transformation;

import com.example.productService.Models.Setting;
import com.example.productService.RequestDto.SettingDTO;

public class SettingTransformation {

    public static Setting SettingConvertEntity(SettingDTO settingDTO){

        Setting setting=Setting.builder()
                .email(settingDTO.getEmail())
                .alertShowMonthsWise(settingDTO.getAlertShowMonthsWise())
                .build();

        return setting;
    }
}
