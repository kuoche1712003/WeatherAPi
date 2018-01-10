package com.my.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.my.weather.domain.CwbOpenData;

public interface CwbOpenDataRepository extends JpaRepository<CwbOpenData, String> {
    
}
