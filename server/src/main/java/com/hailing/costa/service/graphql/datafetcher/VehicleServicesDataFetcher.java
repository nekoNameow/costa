package com.hailing.costa.service.graphql.datafetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hailing.costa.service.ServiceServiceImpl;
import com.hailing.costa.service.VehicleServiceImpl;
import com.hailing.costa.entity.ServiceEntity;
import com.hailing.costa.entity.VehicleEntity;
import com.hailing.costa.service.graphql.GraphQLException;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class VehicleServicesDataFetcher implements DataFetcher<List<ServiceEntity>> {
  @Autowired
  private VehicleServiceImpl vehicleService;
  @Autowired
  private ServiceServiceImpl serviceService;

  @Override
  public List<ServiceEntity> get(DataFetchingEnvironment dataFetchingEnvironment) {
    String id = dataFetchingEnvironment.getArgument("id");
    if (id == null || id.length() == 0) {
      throw new GraphQLException(400, "id missing from request");
    }

    Map<String, VehicleEntity> vehicleMap = this.vehicleService.getMap();
    VehicleEntity vehicle = vehicleMap.get(id);
    if (vehicle == null) {
      throw new GraphQLException(400, "vehicle id not found");
    }

    String username = dataFetchingEnvironment.getGraphQlContext().get("username");
    if (!this.vehicleService.checkAuthority(username, vehicle)) {
      throw new GraphQLException(401, "attempt to access a vehicle you are unauthorised to access");
    }

    Map<String, List<ServiceEntity>> serviceMap = this.serviceService.getMap();
    List<ServiceEntity> list = serviceMap.get(id);
    if (list == null) {
      list = new ArrayList<ServiceEntity>();
    }

    return list;
  }
}
