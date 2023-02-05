package com.hailing.costa.service.graphql;

import java.io.File;
import javax.annotation.PostConstruct;

import com.google.gson.Gson;
import com.hailing.costa.service.graphql.datafetcher.CheckLoginDataFetcher;
import com.hailing.costa.service.graphql.datafetcher.LoginDataFetcher;
import com.hailing.costa.service.graphql.datafetcher.ServiceListDataFetcher;
import com.hailing.costa.service.graphql.datafetcher.VehicleDataFetcher;
import com.hailing.costa.service.graphql.datafetcher.VehicleListDataFetcher;
import com.hailing.costa.service.graphql.datafetcher.VehicleServicesDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class GraphQLService {
  @Value("classpath:schema.graphql")
  private Resource resource;

  @Getter
  private GraphQL graphQL;

  @Autowired
  private VehicleListDataFetcher vehicleListDataFetcher;
  @Autowired
  private VehicleDataFetcher vehicleDataFetcher;
  @Autowired
  private ServiceListDataFetcher serviceListDataFetcher;
  @Autowired
  private VehicleServicesDataFetcher vehicleServicesDataFetcher;
  @Autowired
  private LoginDataFetcher loginDataFetcher;
  @Autowired
  private CheckLoginDataFetcher checkloginDataFetcher;

  @PostConstruct
  private void loadSchema() {
    try {
      File schemaFile = resource.getFile();
      TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
      RuntimeWiring wiring = this.buildRuntimeWiring();
      GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
      this.graphQL = GraphQL.newGraphQL(schema).build();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private RuntimeWiring buildRuntimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
            .type(
                    "Query",
                    typeWiring -> typeWiring
                            .dataFetcher("vehicleList", vehicleListDataFetcher)
                            .dataFetcher("vehicle", vehicleDataFetcher)
                            .dataFetcher("serviceList", serviceListDataFetcher)
                            .dataFetcher("vehicleServices", vehicleServicesDataFetcher)
                            .dataFetcher("login", loginDataFetcher)
                            .dataFetcher("checkLogin", checkloginDataFetcher))
            .build();
  }

  public String parseQuery(String str) {
    GraphQLQuery obj = new Gson().fromJson(str, GraphQLQuery.class);
    return obj.query;
  }

  @Getter
  private class GraphQLQuery {
    private String query;
  }
}
