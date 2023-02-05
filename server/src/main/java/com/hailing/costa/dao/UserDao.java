package com.hailing.costa.dao;

import com.hailing.costa.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends BaseDao<UserEntity> {
  public UserDao() {
    super(UserEntity.class);
    this.TABLE_NAME = "user";
  }

}
