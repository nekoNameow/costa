package com.hailing.costa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.ServiceEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ServiceDao extends BaseDao<ServiceEntity> {
    public ServiceDao() {
        super(ServiceEntity.class);
    }

    @Cacheable(cacheNames = "serviceList")
    public List<ServiceEntity> getList() {
        return this.find();
    }

    @Cacheable(cacheNames = "serviceMap")
    public Map<String, ServiceEntity> getMap() {
        Map<String, ServiceEntity> map = new HashMap<>();
        List<ServiceEntity> list = this.find();
        list.forEach(item -> map.put(item.getId(), item));
        return map;
    }
}
