package com.hailing.costa.worker.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hailing.costa.service.UserServiceImpl;

@Component
public class InitUserData {
  @Autowired
  private UserServiceImpl userService;

  @PostConstruct
  public void run() {
    this.userService.create("test", "123456", "123456");
  }
}
