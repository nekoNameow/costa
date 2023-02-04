package com.hailing.costa.worker.api;

import java.util.List;

import com.hailing.costa.entity.VehicleEntity;

public class ApiVehicleList {
  private List<VehicleEntity> vehicles;
  public static String url = "/list";

  public ApiVehicleList(List<VehicleEntity> vehicles) {
    this.vehicles = vehicles;
  }

  public List<VehicleEntity> getVehicles() {
    return vehicles;
  }
}
