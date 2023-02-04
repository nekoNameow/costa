package com.hailing.costa.dao;

import org.springframework.stereotype.Component;

import com.hailing.costa.entity.VehicleEntity;

@Component
public class VehicleDao extends BaseDao<VehicleEntity> {
  public VehicleDao() {
    super(VehicleEntity.class);
  }
}
