package com.hailing.costa.dao;

import com.hailing.costa.entity.ServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceDao extends BaseDao<ServiceEntity> {
  public ServiceDao() {
    super(ServiceEntity.class);
    this.TABLE_NAME = "service";
  }

  // public List<VehicleServiceEntity> getVehiclesByServiceNameStatus(String name,
  // String status) {
  // LookupOperation lookupOperation = LookupOperation.newLookup()
  // .from("service")
  // .localField("serviceId")
  // .foreignField("_id")
  // .as("result");
  // AggregationOperation match = Aggregation.match(Criteria
  // .where("result.serviceName").is(name)
  // .orOperator(Criteria.where("status").is(status)));
  // Aggregation aggregation = Aggregation.newAggregation(lookupOperation, match);
  // return this.aggregate(aggregation).getMappedResults();
  // }
}
