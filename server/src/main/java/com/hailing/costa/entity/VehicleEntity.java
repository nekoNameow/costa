package com.hailing.costa.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("vehicle")
public class VehicleEntity implements IEntity {
    @Id
    private String id;
    private String name;
    private String msidn;
    private String engineStatus;
    private String fleet;
    private String brand;
    private String countryOfOperation;
    private String chassisNumber;
    private String cassisSeries;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("name", name);
        map.put("msidn", msidn);
        map.put("engineStatus", engineStatus);
        map.put("fleet", fleet);
        map.put("brand", brand);
        map.put("countryOfOperation", countryOfOperation);
        map.put("chassisNumber", chassisNumber);
        map.put("cassisSeries", cassisSeries);
        return map;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMsidn() {
        return msidn;
    }

    public String getEngineStatus() {
        return engineStatus;
    }

    public String getFleet() {
        return fleet;
    }

    public String getBrand() {
        return brand;
    }

    public String getCountryOfOperation() {
        return countryOfOperation;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public String getCassisSeries() {
        return cassisSeries;
    }

    public void setId(String val) {
        this.id = val;
    }

    public void setName(String val) {
        this.name = val;
    }

    public void setMsidn(String val) {
        this.msidn = val;
    }

    public void setEngineStatus(String val) {
        this.engineStatus = val;
    }

    public void setFleet(String val) {
        this.fleet = val;
    }

    public void setBrand(String val) {
        this.brand = val;
    }

    public void setCountryOfOperation(String val) {
        this.countryOfOperation = val;
    }

    public void setChassisNumber(String val) {
        this.chassisNumber = val;
    }

    public void setCassisSeries(String val) {
        this.cassisSeries = val;
    }
}
