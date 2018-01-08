package com.my.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.weather.domain.CwbOpenData;
@Repository
public interface CwbOpenDataRepository extends JpaRepository<CwbOpenData, String> {
    
}
