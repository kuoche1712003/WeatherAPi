package com.my.weather.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.my.weather.domain.Location;


public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByLocationName(String locationName);
    List<Location> findByLocationNameAndTimeObsTime(String locationName,Date obsTime);
    Long countByLocationNameAndTimeObsTime(String locationName,Date obsTime);
}
