package com.my.weather.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.my.weather.domain.Location;
import com.my.weather.repository.LocationRepository;

@RestController
public class LocationController {
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+08:00'");
    
   
    @Autowired
    LocationRepository repo;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    
    //取得所有locationName
    @RequestMapping(value="/getLocationName")
    public List<String> getlocationName(){
        List<Location> data = repo.findAll();
        List<String> locationname = new ArrayList<String>();
        for( Location location : data ) {
            locationname.add(location.getLocationName());
        }
        return locationname;
    }
    //查詢location以locationName和obsTime
    @RequestMapping(value="/findByLocationNameAndTime")
    public List<Location> findLocationByNameAndTime(@RequestParam String locationName, @RequestParam String obsTime) throws ParseException{
            return repo.findByLocationNameAndTimeObsTime(locationName, format.parse(obsTime));   
    }
    //取得名稱為?的所有obsTime
    @RequestMapping(value="/getObsTimebyLocationName")
    public Set<String> getObsTimeByLocationName(@RequestParam String locationName){
        Set<String> obsTime =  new HashSet<String>(); 
        format.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        List<Location> location = repo.findByLocationName(locationName);
        for( Location lo : location ) {
            //利用set不重複特性取得刪除重複值
            obsTime.add(format.format(lo.getTime().getObsTime()));
        }
        return obsTime;
    }
    
}
