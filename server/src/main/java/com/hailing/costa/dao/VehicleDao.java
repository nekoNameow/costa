package com.hailing.costa.dao;

import com.hailing.costa.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleDao extends BaseDao<VehicleEntity> {
  public VehicleDao() {
    super(VehicleEntity.class);
    this.TABLE_NAME = "vehicle";
  }
}
