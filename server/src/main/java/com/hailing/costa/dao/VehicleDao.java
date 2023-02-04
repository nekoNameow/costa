package com.hailing.costa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.VehicleEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class VehicleDao extends BaseDao<VehicleEntity> {
    public VehicleDao() {
        super(VehicleEntity.class);
        this.TABLE_NAME = "vehicle";
    }

    @Cacheable(cacheNames = "vehicleList")
    public List<VehicleEntity> getList() {
        return this.find();
    }

    @Cacheable(cacheNames = "vehicleMap")
    public Map<String, VehicleEntity> getMap() {
        Map<String, VehicleEntity> map = new HashMap<>();
        List<VehicleEntity> list = this.find();
        list.forEach(item -> {
            map.put(item.getId(), item);
        });
        return map;
    }
}
