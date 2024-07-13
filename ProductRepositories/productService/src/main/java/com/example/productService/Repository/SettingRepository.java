package com.example.productService.Repository;

import com.example.productService.Models.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SettingRepository  extends JpaRepository<Setting, Integer> {
    Optional<Setting> findByEmail(String email);
}
