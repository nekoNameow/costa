package com.hailing.costa.service.graphql.datafetcher;

import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.ServiceEntity;
import com.hailing.costa.entity.VehicleEntity;
import com.hailing.costa.service.ServiceServiceImpl;
import com.hailing.costa.service.VehicleServiceImpl;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceListDataFetcher implements DataFetcher<List<ServiceEntity>> {
  @Autowired
  private ServiceServiceImpl serviceService;
  @Autowired
  private VehicleServiceImpl vehicleService;

  @Override
  public List<ServiceEntity> get(DataFetchingEnvironment dataFetchingEnvironment) {
    String username = dataFetchingEnvironment.getGraphQlContext().get("username");
    Map<String, VehicleEntity> map = this.vehicleService.getMap();

    return this.serviceService.getList().stream().filter(item -> {
      VehicleEntity vehicle = map.get(item.getVehicleId());
      if (vehicle == null) {
        return false;
      }
      if (!this.vehicleService.checkAuthority(username, vehicle)) {
        return false;
      }
      return true;
    }).toList();
  }
}
