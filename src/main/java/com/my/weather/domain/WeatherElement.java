package com.my.weather.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WeatherElement {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String elementName;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "WEATHER_ELEMENT_ID" )
    private List<ElementValue> elementValue;
    
    
    public String getElementName() {
        return elementName;
    }
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
    public List<ElementValue> getElementValue() {
        return elementValue;
    }
    public void setElementValue(List<ElementValue> elementValue) {
        this.elementValue = elementValue;
    }
    
}
