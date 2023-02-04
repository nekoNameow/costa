package com.hailing.costa.worker.api;

import com.hailing.costa.entity.VehicleEntity;

public class ApiVehicleList {
  private VehicleEntity[] vehicles;
  public static String url = "/list";

  public VehicleEntity[] getVehicles() {
    return vehicles;
  }
}
