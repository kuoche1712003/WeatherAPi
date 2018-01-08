package com.my.weather.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Time {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'+08:00'", locale = "zh", timezone = "GMT+8")
    private Date obsTime;

    public Date getObsTime() {
        return obsTime;
    }

    public void setObsTime(Date obsTime) {
        this.obsTime = obsTime;
    }
    
}
