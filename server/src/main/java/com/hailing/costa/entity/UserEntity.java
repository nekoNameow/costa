package com.hailing.costa.entity;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class UserEntity implements IEntity {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String salt;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("_id", _id);
        map.put("username", username);
        map.put("password", password);
        map.put("salt", salt);
        return map;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }
}
