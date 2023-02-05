package com.hailing.costa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class VehicleServiceImpl implements IVehicleService {
  @Autowired
  private VehicleDao vehicleDao;

  @Override
  @Cacheable(cacheNames = "vehicleList")
  public List<VehicleEntity> getList() {
    return this.vehicleDao.find();
  }

  @Override
  @Cacheable(cacheNames = "vehicleMap")
  public Map<String, VehicleEntity> getMap() {
    Map<String, VehicleEntity> map = new HashMap<>();
    List<VehicleEntity> list = this.vehicleDao.find();
    list.forEach(item -> map.put(item.getId(), item));
    return map;
  }

  @Override
  public void insert(VehicleEntity data) {
    this.vehicleDao.insert(data);
  }

  @Override
  public void updateById(String id, VehicleEntity data) {
    this.vehicleDao.updateById(id, data);
  }

  @Override
  public Boolean checkAuthority(String username, VehicleEntity data) {
    String status = data.getEngineStatus();
    return username.length() > 0 || (status != null && status.equals("OK"));
  }
}
