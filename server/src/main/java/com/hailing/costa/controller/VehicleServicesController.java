package com.hailing.costa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.hailing.costa.dao.ServiceDao;
import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.dao.VehicleServiceDao;
import com.hailing.costa.entity.ServiceEntity;
import com.hailing.costa.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VehicleServicesController {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private ServiceDao serviceDao;
    @Autowired
    private VehicleServiceDao vehicleServiceDao;

    @ResponseBody
    @RequestMapping(path = "/vehicle/services", method = RequestMethod.GET)
    public ResponseEntity<String> main(@RequestParam() String id) {
        if (id.isBlank()) {
            return ResponseEntity.status(400).body("id missing from request");
        }

        Map<String, VehicleEntity> vehicleMap = this.vehicleDao.getMap();
        VehicleEntity vehicle = vehicleMap.get(id);
        if (vehicle == null) {
            return ResponseEntity.status(404).body("vehicle id not found");
        }

        Map<String, ServiceEntity> serviceMap = this.serviceDao.getMap();
        Map<String, List<String>> vehicleServiceMap = this.vehicleServiceDao.getMap();
        List<String> serviceIds = vehicleServiceMap.get(id);
        List<ServiceEntity> list = new ArrayList<ServiceEntity>();
        serviceIds.forEach(x -> {
            ServiceEntity item = serviceMap.get(x);
            if (item == null) {
                return;
            }
            list.add(item);
        });

        return ResponseEntity.status(200).body(new Gson().toJson(list));
    }
}