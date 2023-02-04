package com.hailing.costa.worker.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hailing.costa.dao.ServiceDao;
import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.ServiceEntity;
import com.hailing.costa.entity.VehicleEntity;
import com.hailing.costa.utils.HttpRequest;

@Async
@Component
@EnableScheduling
public class RequestData {
  final private String BASE_URL = "http://localhost:1337/vehicle";
  @Autowired
  private VehicleDao vehicleDao;
  @Autowired
  private ServiceDao serviceDao;

  private Gson gson;
  private HttpRequest client;

  @CacheEvict(cacheNames = {
      "vehicleList",
      "vehicleMap",
      "serviceList",
      "serviceMap"
  }, beforeInvocation = false)
  @Scheduled(initialDelay = 0, fixedDelay = 60 * 60 * 10000)
  public void run() {
    this.gson = new Gson();
    this.client = new HttpRequest();

    // Vehicle List
    VehicleEntity[] vehicleList = this.getVehicleList();

    // Vehicle Info
    for (int i = 0; i < vehicleList.length; i++) {
      this.getVehicleInfo(vehicleList[i]);
    }

    // Vehicle Service
    for (int i = 0; i < vehicleList.length; i++) {
      this.getVehicleServices(vehicleList[i]);
    }
  }

  /**
   * Get all the vehicles
   */
  private VehicleEntity[] getVehicleList() {
    Map<String, VehicleEntity> map = this.vehicleDao.getMap();
    String str = this.client.doGet(BASE_URL + ApiVehicleList.url);
    ApiVehicleList json = this.gson.fromJson(str, ApiVehicleList.class);
    if (json == null) {
      System.out.println("Get Vehicle List Failed!");
      return new VehicleEntity[0];
    }
    VehicleEntity[] list = json.getVehicles();
    int count = 0;
    for (int i = 0; i < list.length; i++) {
      VehicleEntity item = list[i];
      if (map.get(item.getId()) == null) {
        this.vehicleDao.insert(item);
        count++;
      }
    }
    if (count > 0) {
      System.out.println("Update Vehicle List Success (" + count + ")");
    }
    return list;
  }

  /**
   * Get the infomation of the vehicle
   */
  private void getVehicleInfo(VehicleEntity item) {
    String identy = item.getId() + ": " + item.getName();
    String str = client.doGet(BASE_URL + ApiVehicleInfo.url + "?id=" + item.getId());
    ApiVehicleInfo json = this.gson.fromJson(str, ApiVehicleInfo.class);
    if (json == null) {
      System.out.println("Get Vehicle Info Failed - " + identy);
      return;
    }
    this.vehicleDao.updateById(item.getId(), json.getEntity());
    System.out.println("Update Vehicle Info Success - " + identy);
  }

  /**
   * Get all the services of the vehicle
   */
  private void getVehicleServices(VehicleEntity item) {
    String identy = item.getId() + ": " + item.getName();
    String str = client.doGet(BASE_URL + ApiVehicleServices.url + "?id=" + item.getId());
    ApiVehicleServices json = this.gson.fromJson(str, ApiVehicleServices.class);
    if (json == null) {
      System.out.println("Get Vehicle Sertices Failed - " + identy);
      return;
    }

    // Delete all the relation between vehicle and service
    this.serviceDao.deleteByVehicleId(item.getId());

    // Insert
    ServiceEntity[] list = json.getServices();
    for (int i = 0; i < list.length; i++) {
      ServiceEntity service = list[i];
      service.setVehicleId(item.getId());
      this.serviceDao.insert(service);
    }
    System.out.println("Update Vehicle Services Success - " + identy);
  }
}
