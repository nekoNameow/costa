package com.hailing.costa.service.graphql.datafetcher;

import com.hailing.costa.entity.UserEntity;
import com.hailing.costa.service.UserServiceImpl;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckLoginDataFetcher implements DataFetcher<UserEntity> {
  @Autowired
  private UserServiceImpl userService;

  @Override
  public UserEntity get(DataFetchingEnvironment dataFetchingEnvironment) {
    String username = dataFetchingEnvironment.getGraphQlContext().get("username");
    if (username.length() == 0) {
      return null;
    }
    UserEntity user = this.userService.getByUsername(username);
    // Hide the private info
    user.setPassword("*");
    user.setSalt("*");
    return user;
  }
}
