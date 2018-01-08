package com.my.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.my.weather.domain.Location;
@RepositoryRestResource(path="locations")
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByLocationName(@Param("locationName")String locationName);
    
}
