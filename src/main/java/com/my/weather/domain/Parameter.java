package com.my.weather.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Parameter {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String parameterName;
    private String parameterValue;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getParameterName() {
        return parameterName;
    }
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
    public String getParameterValue() {
        return parameterValue;
    }
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
    
}
