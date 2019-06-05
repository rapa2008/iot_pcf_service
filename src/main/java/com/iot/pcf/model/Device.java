package com.iot.pcf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Device {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("token")
    private String token;
    @JsonProperty("name")
    private String name;
    @JsonProperty("model")
    private String model;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Date createdDate;

    public Device(){}

    public Device(String id, String name, String model, String type, Date createdDate, String token) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.model = model;
        this.type = type;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", token=" + token +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
