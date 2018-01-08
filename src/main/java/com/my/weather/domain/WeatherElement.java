package com.my.weather.domain;


public class WeatherElement {
    private String elementName;
    private ElementValue elementValue;
    public String getElementName() {
        return elementName;
    }
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
    public ElementValue getElementValue() {
        return elementValue;
    }
    public void setElementValue(ElementValue elementValue) {
        this.elementValue = elementValue;
    }
    
}
