package com.my.weather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.weather.domain.Location;
import com.my.weather.repository.LocationRepository;

@Controller
public class LocationController {
    @Autowired
    LocationRepository repo;
    
    @RequestMapping(value="/index")
    public String index() {
        
        return "index";
    }
    @RequestMapping(value="/getLocationName")
    public @ResponseBody List<String> locationName(){
        List<Location> data = repo.findAll();
        List<String> locationname = new ArrayList<String>();
        for( Location location : data ) {
            locationname.add(location.getLocationName());
        }
        return locationname;
    }
}
