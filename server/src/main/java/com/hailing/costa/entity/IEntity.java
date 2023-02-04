package com.hailing.costa.entity;

import java.util.Map;

public interface IEntity {
  public String getId();

  public Map<String, Object> toMap();
}
