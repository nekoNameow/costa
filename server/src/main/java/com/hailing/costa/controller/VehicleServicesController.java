package com.hailing.costa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hailing.costa.dao.ServiceDao;
import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.ServiceEntity;
import com.hailing.costa.entity.VehicleEntity;

@Controller
public class VehicleServicesController {
  @Autowired
  private VehicleDao vehicleDao;
  @Autowired
  private ServiceDao serviceDao;

  @ResponseBody
  @RequestMapping(path = "/vehicle/services", method = RequestMethod.GET)
  public ResponseEntity<String> main(@RequestParam(required = true, value = "") String id) {
    if (id.equals("")) {
      return ResponseEntity.status(400).body("id missing from request");
    }

    Map<String, VehicleEntity> vehicleMap = this.vehicleDao.getMap();
    VehicleEntity vehicle = vehicleMap.get(id);
    if (vehicle == null) {
      return ResponseEntity.status(404).body("vehicle id not found");
    }

    Map<String, List<ServiceEntity>> serviceMap = this.serviceDao.getMap();
    List<ServiceEntity> list = serviceMap.get(id);
    if (list == null) {
      list = new ArrayList<ServiceEntity>();
    }

    return ResponseEntity.status(200).body(new Gson().toJson(list));
  }
}