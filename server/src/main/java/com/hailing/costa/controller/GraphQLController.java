package com.hailing.costa.controller;

import java.util.HashMap;
import java.util.Map;

import com.hailing.costa.service.graphql.GraphQLService;
import com.hailing.costa.utils.JwtUtils;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GraphQLController {
  @Autowired
  private GraphQLService graphQLService;

  @ResponseBody
  @RequestMapping(path = "/graphql", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<Object> main(
          @RequestBody String query,
          @RequestHeader(value = "token") String token) {
    // Get username from token
    Map<String, Object> variables = new HashMap<>();
    variables.put("username", JwtUtils.decrypt(token));

    // Build
    String queryStr = this.graphQLService.parseQuery(query);
    ExecutionInput executionInput = ExecutionInput.newExecutionInput()
            .query(queryStr)
            .graphQLContext(variables)
            .build();
    ExecutionResult execute = graphQLService.getGraphQL().execute(executionInput);
    return new ResponseEntity<>(execute, HttpStatus.OK);
  }
}
