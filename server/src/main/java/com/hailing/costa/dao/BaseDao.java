package com.hailing.costa.dao;

import java.util.List;
import java.util.Map;

import com.hailing.costa.entity.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public abstract class BaseDao<T extends IEntity> {
    @Autowired
    private MongoTemplate mongoTemplate;
    private Class<T> entity;
    protected String TABLE_NAME;

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

    public void save(T data) {
        mongoTemplate.save(data);
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
            if (value == null || key == "id" || key == "_id") {
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

    public void delete(Query query) {
        if (query == null) {
            query = new Query();
        }
        mongoTemplate.remove(query, this.entity);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        this.delete(query);
    }

    public AggregationResults<T> aggregate(Aggregation aggregate) {
        return this.mongoTemplate.aggregate(aggregate, this.TABLE_NAME, this.entity);
    }
}
