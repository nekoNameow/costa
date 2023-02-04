package com.hailing.costa.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hailing.costa.entity.IEntity;

public abstract class BaseDao<T extends IEntity> {
  @Autowired
  private MongoTemplate mongoTemplate;
  private Class<T> entity;

  public BaseDao(Class<T> entity) {
    this.entity = entity;
  }

  public List<T> find(Query query) {
    if (query == null) {
      query = new Query();
    }
    return mongoTemplate.find(query, this.entity);
  }

  public List<T> find() {
    return this.find(null);
  }

  public T findOne(Query query) {
    List<T> list = this.find(query);
    if (list.isEmpty()) {
      return null;
    }
    return list.get(0);
  }

  public T findOne() {
    return this.findOne(null);
  }

  public T findById(String id) {
    return mongoTemplate.findById(id, this.entity);
  }

  public void insert(T data) {
    try {
      mongoTemplate.insert(data);
    } catch (Exception e) {
    }
  }

  public void update(Query query, T data) {
    if (query == null) {
      query = new Query();
    }
    Map<String, Object> map = data.toMap();
    Update update = new Update();
    map.entrySet().forEach(item -> {
      String key = item.getKey();
      Object value = item.getValue();
      if (value == null || key == "id") {
        return;
      }
      update.set(key, value);
    });
    mongoTemplate.updateMulti(query, update, this.entity);
  }

  public void updateById(String id, T data) {
    Query query = new Query(Criteria.where("_id").is(id));
    this.update(query, data);
  }
}
