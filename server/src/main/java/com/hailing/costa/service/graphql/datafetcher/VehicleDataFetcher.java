package com.hailing.costa.service.graphql.datafetcher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import com.hailing.costa.service.VehicleServiceImpl;
import com.hailing.costa.entity.VehicleEntity;
import com.hailing.costa.service.graphql.GraphQLException;

@Component
public class VehicleDataFetcher implements DataFetcher<VehicleEntity> {
  @Autowired
  private VehicleServiceImpl vehicleService;

  @Override
  public VehicleEntity get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
    String id = dataFetchingEnvironment.getArgument("id");
    if (id == null) {
      throw new GraphQLException(400, "id missing from request");
    }

    Map<String, VehicleEntity> map = this.vehicleService.getMap();
    VehicleEntity vehicle = map.get(id);

    if (vehicle == null) {
      throw new GraphQLException(404, "vehicle id not found");
    }

    String username = dataFetchingEnvironment.getGraphQlContext().get("username");
    if (!this.vehicleService.checkAuthority(username, vehicle)) {
      throw new GraphQLException(401, "attempt to access a vehicle you are unauthorised to access");
    }

    return vehicle;
  }
}
