package com.hailing.costa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.ServiceEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ServiceDao extends BaseDao<ServiceEntity> {
    public ServiceDao() {
        super(ServiceEntity.class);
        this.TABLE_NAME = "service";
    }

    @Cacheable(cacheNames = "serviceList")
    public List<ServiceEntity> getList() {
        return this.find();
    }

    @Cacheable(cacheNames = "serviceMap")
    public Map<String, List<ServiceEntity>> getMap() {
        Map<String, List<ServiceEntity>> map = new HashMap<>();
        List<ServiceEntity> list = this.find();
        list.forEach(item -> {
            String vehicleId = item.getVehicleId();
            if (!map.containsKey(vehicleId)) {
                map.put(vehicleId, new ArrayList<>());
            }
            map.get(vehicleId).add(item);
        });
        return map;
    }

    public void deleteByVehicleId(String id) {
        Query query = new Query(Criteria.where("vehicleId").is(id));
        this.delete(query);
    }

    public List<ServiceEntity> findByNameStatus(String name, String status) {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("serviceName").is(name),
                Criteria.where("status").is(status)));
        return this.find(query);
    }

    public List<ServiceEntity> findByName(String name) {
        Query query = new Query(Criteria.where("serviceName").is(name));
        return this.find(query);
    }

    public List<ServiceEntity> findByStatus(String status) {
        Query query = new Query(Criteria.where("status").is(status));
        return this.find(query);
    }

    // public List<VehicleServiceEntity> getVehiclesByServiceNameStatus(String name,
    // String status) {
    // LookupOperation lookupOperation = LookupOperation.newLookup()
    // .from("service")
    // .localField("serviceId")
    // .foreignField("_id")
    // .as("result");
    // AggregationOperation match = Aggregation.match(Criteria
    // .where("result.serviceName").is(name)
    // .orOperator(Criteria.where("status").is(status)));
    // Aggregation aggregation = Aggregation.newAggregation(lookupOperation, match);
    // return this.aggregate(aggregation).getMappedResults();
    // }
}
