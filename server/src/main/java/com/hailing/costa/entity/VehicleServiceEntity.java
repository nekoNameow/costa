package com.hailing.costa.entity;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("vehicle_service")
public class VehicleServiceEntity implements IEntity {
    @Id
    private String _id;
    private String serviceId;
    private String vehicleId;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("_id", _id);
        map.put("serviceId", serviceId);
        map.put("vehicleId", vehicleId);
        return map;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }
}
