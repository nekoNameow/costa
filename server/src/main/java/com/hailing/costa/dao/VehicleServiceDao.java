package com.hailing.costa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.VehicleServiceEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class VehicleServiceDao extends BaseDao<VehicleServiceEntity> {
    public VehicleServiceDao() {
        super(VehicleServiceEntity.class);
    }

    public void deleteByVehicleId(String id) {
        Query query = new Query(Criteria.where("vehicleId").is(id));
        this.delete(query);
    }

    @Cacheable(cacheNames = "vehicleServiceRelation")
    public Map<String, List<String>> getMap() {
        Map<String, List<String>> map = new HashMap<>();
        List<VehicleServiceEntity> list = this.find();
        list.forEach(item -> {
            String vId = item.getVehicleId();
            String sId = item.getServiceId();
            if (!map.containsKey(vId)) {
                map.put(vId, new ArrayList<>());
            }
            map.get(vId).add(sId);
        });
        return map;
    }
}
