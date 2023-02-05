package com.hailing.costa.service;

import com.hailing.costa.entity.UserEntity;

public interface IUserService {
  public String encryptPassword(String password, String salt);

  public void create(String username, String password, String salt);

  public UserEntity getByUsername(String username);

  public String login(String username, String password);
}
