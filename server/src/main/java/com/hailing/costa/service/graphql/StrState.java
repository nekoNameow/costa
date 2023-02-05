package com.hailing.costa.service.graphql;

import lombok.Data;

@Data
public class StrState {
  private String str;

  public StrState(String str) {
    this.str = str;
  }
}
