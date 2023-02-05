package com.hailing.costa.service;

import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.VehicleEntity;

public interface IVehicleService {
  public List<VehicleEntity> getList();

  public Map<String, VehicleEntity> getMap();

  public void insert(VehicleEntity data);

  public void updateById(String id, VehicleEntity data);

  public Boolean checkAuthority(String username, VehicleEntity data);
}
