package com.my.weather.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CwbOpenData {
    private String identifier;
    private String sender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'+08:00'", locale = "zh", timezone = "GMT+8")
    private Date sent;
    private String status;
    private String msgType;
    private String dataid;
    private String scope;
    private String dataset;
    private Location[] location;
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public Date getSent() {
        return sent;
    }
    public void setSent(Date sent) {
        this.sent = sent;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMsgType() {
        return msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public String getDataid() {
        return dataid;
    }
    public void setDataid(String dataid) {
        this.dataid = dataid;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getDataset() {
        return dataset;
    }
    public void setDataset(String dataset) {
        this.dataset = dataset;
    }
    public Location[] getLocation() {
        return location;
    }
    public void setLocation(Location[] location) {
        this.location = location;
    }
    
}
