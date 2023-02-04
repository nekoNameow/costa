package com.hailing.costa.worker.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.VehicleEntity;
import com.hailing.costa.utils.HttpRequest;

@Async
@Component
@EnableScheduling
public class RequestData extends Thread {
  final private String BASE_URL = "http://localhost:1337/vehicle";
  @Autowired
  private VehicleDao vehicleDao;

  private Gson gson;
  private HttpRequest client;

  @Scheduled(cron = "0/5 * * * * ?")
  @Override
  public void run() {
    request();
  }

  private void request() {
    this.gson = new Gson();
    this.client = new HttpRequest();

    // Vehicle List
    VehicleEntity[] vehicleList = this.getVehicleList();

    // Vehicle Info
    for (int i = 0; i < vehicleList.length; i++) {
      this.getVehicleInfo(vehicleList[i]);
    }
  }

  private VehicleEntity[] getVehicleList() {
    String str = this.client.doGet(BASE_URL + ApiVehicleList.url);
    ApiVehicleList json = this.gson.fromJson(str, ApiVehicleList.class);
    if (json == null) {
      System.out.println("Get Vehicle List Failed!");
      return new VehicleEntity[0];
    }
    VehicleEntity[] list = json.getVehicles();
    for (int i = 0; i < list.length; i++) {
      this.vehicleDao.insert(list[i]);
    }
    System.out.println("Get Vehicle List Success!");
    return list;
  }

  private void getVehicleInfo(VehicleEntity item) {
    String identy = item.getId() + ": " + item.getName();
    String str = client.doGet(BASE_URL + ApiVehicleInfo.url + "?id=" + item.getId());
    ApiVehicleInfo json = this.gson.fromJson(str, ApiVehicleInfo.class);
    if (json == null) {
      System.out.println("Get Vehicle Info Failed - " + identy);
      return;
    }
    this.vehicleDao.updateById(item.getId(), json.getEntity());
    System.out.println("Get Vehicle Info Success - " + identy);
  }
}
