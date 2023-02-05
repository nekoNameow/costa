package com.hailing.costa.service.graphql.datafetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hailing.costa.service.ServiceServiceImpl;
import com.hailing.costa.service.VehicleServiceImpl;
import com.hailing.costa.entity.ServiceEntity;
import com.hailing.costa.entity.VehicleEntity;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class VehicleListDataFetcher implements DataFetcher<List<VehicleEntity>> {
  @Autowired
  private VehicleServiceImpl vehicleService;
  @Autowired
  private ServiceServiceImpl serviceService;

  @Override
  public List<VehicleEntity> get(DataFetchingEnvironment dataFetchingEnvironment) {
    List<VehicleEntity> all = this.vehicleService.getList();
    String serviceName = dataFetchingEnvironment.getArgument("serviceName");
    String serviceStatus = dataFetchingEnvironment.getArgument("serviceStatus");
    String name = dataFetchingEnvironment.getArgument("name");

    Map<String, Boolean> vehicleIds = new HashMap<String, Boolean>();
    Boolean hasServiceName = serviceName != null && serviceName.length() > 0;
    Boolean hasServiceStatus = serviceStatus != null && serviceStatus.length() > 0;
    List<ServiceEntity> serviceList = new ArrayList<ServiceEntity>();
    if (hasServiceName && hasServiceStatus) {
      serviceList = this.serviceService.findByNameStatus(serviceName, serviceStatus);
    } else if (hasServiceName) {
      serviceList = this.serviceService.findByName(serviceName);
    } else if (hasServiceStatus) {
      serviceList = this.serviceService.findByStatus(serviceStatus);
    }
    serviceList.forEach(x -> vehicleIds.put(x.getVehicleId(), true));

    String username = dataFetchingEnvironment.getGraphQlContext().get("username");

    // Filter
    List<VehicleEntity> list = new ArrayList<VehicleEntity>();
    all.forEach(item -> {
      if (name != null && name.length() > 0 && (item.getName() == null || !item.getName().contains(name))) {
        return;
      }
      if ((hasServiceName || hasServiceStatus) && !vehicleIds.containsKey(item.getId())) {
        return;
      }
      if (!this.vehicleService.checkAuthority(username, item)) {
        return;
      }
      list.add(item);
    });

    return list;
  }
}
