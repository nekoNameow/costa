package com.hailing.costa.service.graphql.datafetcher;

import com.hailing.costa.service.UserServiceImpl;
import com.hailing.costa.service.graphql.StrState;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDataFetcher implements DataFetcher<StrState> {
  @Autowired
  private UserServiceImpl userService;

  @Override
  public StrState get(DataFetchingEnvironment dataFetchingEnvironment) {
    String username = dataFetchingEnvironment.getArgument("username");
    String password = dataFetchingEnvironment.getArgument("password");
    return new StrState(this.userService.login(username, password));
  }
}
