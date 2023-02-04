package com.hailing.costa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.VehicleEntity;

@Controller
public class VehicleController {
  @Autowired
  private VehicleDao vehicleDao;

  @ResponseBody
  @RequestMapping(path = "/vehicle", method = RequestMethod.GET)
  public ResponseEntity<String> main(@RequestParam() String id) {
    if (id.isBlank()) {
      return ResponseEntity.status(400).body("id missing from request");
    }
    Map<String, VehicleEntity> map = this.vehicleDao.getMap();
    VehicleEntity vehicle = map.get(id);
    if (vehicle == null) {
      return ResponseEntity.status(404).body("vehicle id not found");
    }
    return ResponseEntity.status(200).body(vehicle.toString());
  }
}