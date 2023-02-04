package com.hailing.costa.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("service")
public class ServiceEntity implements IEntity {
    @Id
    private String _id;
    @Indexed
    private String serviceName;
    @Indexed
    private String status;
    private String vehicleId;
    private Date lastUpdate;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_id", _id);
        map.put("serviceName", serviceName);
        map.put("vehicleId", vehicleId);
        map.put("status", status);
        map.put("lastUpdate", lastUpdate);
        return map;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }
}
