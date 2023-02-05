package com.hailing.costa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.hailing.costa.dao.ServiceDao;

import com.hailing.costa.entity.ServiceEntity;

@Component
public class ServiceServiceImpl implements IServiceService {
  @Autowired
  private ServiceDao serviceDao;

  @Override
  @Cacheable(cacheNames = "serviceList")
  public List<ServiceEntity> getList() {
    return this.serviceDao.find();
  }

  @Override
  @Cacheable(cacheNames = "serviceMap")
  public Map<String, List<ServiceEntity>> getMap() {
    Map<String, List<ServiceEntity>> map = new HashMap<>();
    List<ServiceEntity> list = this.serviceDao.find();
    list.forEach(item -> {
      String vehicleId = item.getVehicleId();
      if (!map.containsKey(vehicleId)) {
        map.put(vehicleId, new ArrayList<>());
      }
      map.get(vehicleId).add(item);
    });
    return map;
  }

  @Override
  public void deleteByVehicleId(String id) {
    Query query = new Query(Criteria.where("vehicleId").is(id));
    this.serviceDao.delete(query);
  }

  @Override
  public List<ServiceEntity> findByNameStatus(String name, String status) {
    Query query = new Query(new Criteria().andOperator(
        Criteria.where("serviceName").is(name),
        Criteria.where("status").is(status)));
    return this.serviceDao.find(query);
  }

  @Override
  public List<ServiceEntity> findByName(String name) {
    Query query = new Query(Criteria.where("serviceName").is(name));
    return this.serviceDao.find(query);
  }

  @Override
  public List<ServiceEntity> findByStatus(String status) {
    Query query = new Query(Criteria.where("status").is(status));
    return this.serviceDao.find(query);
  }

  @Override
  public void insert(ServiceEntity data) {
    this.serviceDao.insert(data);
  }

  @Override
  public void updateById(String id, ServiceEntity data) {
    this.serviceDao.updateById(id, data);
  }
}
