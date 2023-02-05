package com.hailing.costa.service;

import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.ServiceEntity;

public interface IServiceService {
  public List<ServiceEntity> getList();

  public Map<String, List<ServiceEntity>> getMap();

  public void deleteByVehicleId(String id);

  public List<ServiceEntity> findByNameStatus(String name, String status);

  public List<ServiceEntity> findByName(String name);

  public List<ServiceEntity> findByStatus(String status);

  public void updateById(String id, ServiceEntity data);

  public void insert(ServiceEntity data);
}
