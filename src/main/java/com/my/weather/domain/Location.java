package com.my.weather.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Location {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lat;    
    private String lon;
    private String lat_wgs84;
    private String lon_wgs84;
    private String locationName;
    private String stationId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "TIME_ID" )
    private Time time;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "LOCATION_ID" )
    private List<WeatherElement> weatherElement;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "LOCATION_ID" )
    private List<Parameter> parameter;
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getLat_wgs84() {
        return lat_wgs84;
    }
    public void setLat_wgs84(String lat_wgs84) {
        this.lat_wgs84 = lat_wgs84;
    }
    public String getLon_wgs84() {
        return lon_wgs84;
    }
    public void setLon_wgs84(String lon_wgs84) {
        this.lon_wgs84 = lon_wgs84;
    }
    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public String getStationId() {
        return stationId;
    }
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }
    public void setWeatherElement(List<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
    }
    public List<Parameter> getParameter() {
        return parameter;
    }
    public void setParameter(List<Parameter> parameter) {
        this.parameter = parameter;
    }
    
}
