package com.my.weather.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class CwbOpenData {
    @Id
    private String identifier;
    private String sender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'+08:00'", locale = "zh", timezone = "GMT+8")
    private Date sent;
    private String status;
    private String msgType;
    private String dataid;
    private String scope;
    private String dataset;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "CWBOPENDATA_ID" )
    private List<Location> location;
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
    public List<Location> getLocation() {
        return location;
    }
    public void setLocation(List<Location> location) {
        this.location = location;
    }
    
}
