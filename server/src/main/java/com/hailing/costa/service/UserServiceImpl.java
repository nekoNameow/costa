package com.hailing.costa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.hailing.costa.dao.UserDao;
import com.hailing.costa.entity.UserEntity;
import com.hailing.costa.utils.EncryptUtils;
import com.hailing.costa.utils.JwtUtils;

@Component
public class UserServiceImpl implements IUserService {
  @Autowired
  private UserDao userDao;

  @Override
  public String encryptPassword(String password, String salt) {
    return EncryptUtils.md5(EncryptUtils.md5(password) + salt);
  }

  @Override
  public void create(String username, String password, String salt) {
    UserEntity user = new UserEntity();
    user.setUsername(username);
    user.setSalt(salt);
    user.setPassword(this.encryptPassword(password, salt));
    this.userDao.insert(user);
  }

  @Override
  public UserEntity getByUsername(String username) {
    return this.userDao.findOne(new Query(Criteria.where("username").is(username)));
  }

  @Override
  public String login(String username, String password) {
    UserEntity user = this.getByUsername(username);
    if (user == null) {
      return null;
    }
    if (!this.encryptPassword(password, user.getSalt()).equals(user.getPassword())) {
      return null;
    }
    try {
      return JwtUtils.encrypt(username);
    } catch (Exception e) {
      return null;
    }
  }
}
