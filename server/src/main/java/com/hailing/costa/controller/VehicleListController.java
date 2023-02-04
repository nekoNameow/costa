package com.hailing.costa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hailing.costa.dao.ServiceDao;
import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.ServiceEntity;
import com.hailing.costa.entity.VehicleEntity;

@Controller
public class VehicleListController {
  @Autowired
  private VehicleDao vehicleDao;
  @Autowired
  private ServiceDao serviceDao;

  @ResponseBody
  @RequestMapping(path = "/vehicle/list", method = RequestMethod.GET)
  public List<VehicleEntity> main(
      @RequestParam(required = false, value = "") String name,
      @RequestParam(required = false, value = "") String serviceName,
      @RequestParam(required = false, value = "") String serviceStatus) {
    List<VehicleEntity> all = this.vehicleDao.getList();

    Map<String, Boolean> vehicleIds = new HashMap<String, Boolean>();
    Boolean hasServiceName = serviceName != null && serviceName.length() > 0;
    Boolean hasServiceStatus = serviceStatus != null && serviceStatus.length() > 0;
    List<ServiceEntity> serviceList = new ArrayList<ServiceEntity>();
    if (hasServiceName && hasServiceStatus) {
      serviceList = this.serviceDao.findByNameStatus(serviceName, serviceStatus);
    } else if (hasServiceName) {
      serviceList = this.serviceDao.findByName(serviceName);
    } else if (hasServiceStatus) {
      serviceList = this.serviceDao.findByStatus(serviceStatus);
    }
    serviceList.forEach(x -> vehicleIds.put(x.getVehicleId(), true));

    // Filter
    List<VehicleEntity> list = new ArrayList<VehicleEntity>();
    all.forEach(item -> {
      if (name != null && name.length() > 0 && (item.getName() == null || !item.getName().contains(name))) {
        return;
      }
      if ((hasServiceName || hasServiceStatus) && !vehicleIds.containsKey(item.getId())) {
        return;
      }
      list.add(item);
    });

    return list;
  }
}