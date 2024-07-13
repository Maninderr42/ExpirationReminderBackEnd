package com.example.productService.Transformation;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.productService.Models.Setting;
import com.example.productService.RequestDto.SettingDTO;
import org.junit.jupiter.api.Test;

class SettingTransformationDiffblueTest {
    /**
     * Method under test:
     * {@link SettingTransformation#SettingConvertEntity(SettingDTO)}
     */
    @Test
    void testSettingConvertEntity() {
        // Arrange and Act
        Setting actualSettingConvertEntityResult = SettingTransformation.SettingConvertEntity(new SettingDTO());

        // Assert
        assertNull(actualSettingConvertEntityResult.getAlertShowMonthsWise());
        assertNull(actualSettingConvertEntityResult.getSettings());
        assertNull(actualSettingConvertEntityResult.getEmail());
    }
}
