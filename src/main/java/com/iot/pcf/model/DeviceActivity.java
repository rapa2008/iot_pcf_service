package com.iot.pcf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class DeviceActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deviceID;
    private String activity;
    private String action;
    @JsonIgnore
    private Date createdDate;


    public DeviceActivity(){}

    public DeviceActivity(String deviceID, String activity, Date createdDate, String action) {
        this.deviceID = deviceID;
        this.activity = activity;
        this.createdDate = createdDate;
        this.action = action;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "DeviceActivity{" +
                "id=" + id +
                ", deviceID='" + deviceID + '\'' +
                ", activity='" + activity + '\'' +
                ", createdDate=" + createdDate +
                ", action='" + action + '\'' +
                '}';
    }
}
