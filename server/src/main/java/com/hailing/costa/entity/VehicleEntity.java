package com.hailing.costa.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.Gson;

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

    @Override
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

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString () {
        Gson g = new Gson();
        return g.toJson(this).toString();
    }
}
