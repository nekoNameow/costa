package com.hailing.costa.worker.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.VehicleEntity;
import com.hailing.costa.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Async
@Component
@EnableScheduling
public class RequestData {
    final private String BASE_URL = "http://localhost:1337/vehicle";
    @Autowired
    private VehicleDao vehicleDao;
    private Gson gson;
    private HttpRequest client;

    @CacheEvict(cacheNames = {"vehicleList", "vehicleMap"})
    @Scheduled(initialDelay = 0, fixedDelay = 60 * 60 * 10000)
    public void run() {
        this.gson = new Gson();
        this.client = new HttpRequest();

        // Vehicle List
        List<VehicleEntity> vehicleList = this.getVehicleList();

        // Vehicle Info
        for (VehicleEntity vehicle : vehicleList) {
            this.getVehicleInfo(vehicle);
        }
    }

    private List<VehicleEntity> getVehicleList() {
        Map<String, VehicleEntity> map = this.vehicleDao.getMap();
        System.out.println(map.keySet());
        String str = this.client.doGet(BASE_URL + ApiVehicleList.url);
        ApiVehicleList json = this.gson.fromJson(str, ApiVehicleList.class);
        if (json == null) {
            System.out.println("Get Vehicle List Failed!");
            return new ArrayList<>();
        }
        List<VehicleEntity> list = json.getVehicles();
        list.stream().filter(vehicleEntity -> map.get(vehicleEntity.getId()) == null).collect(Collectors.toList());
        if (!list.isEmpty()) {
            list.forEach(vehicleEntity -> this.vehicleDao.insert(vehicleEntity));
            System.out.println("Update Vehicle List Success (" + list.size() + ")");
        }
        return list;
    }

    private void getVehicleInfo(VehicleEntity item) {
        String identity = item.getId() + ": " + item.getName();
        String str = client.doGet(BASE_URL + ApiVehicleInfo.url + "?id=" + item.getId());
        ApiVehicleInfo json = this.gson.fromJson(str, ApiVehicleInfo.class);
        if (json == null) {
            System.out.println("Get Vehicle Info Failed - " + identity);
            return;
        }
        this.vehicleDao.updateById(item.getId(), json.getEntity());
        System.out.println("Update Vehicle Info Success - " + identity);
    }
}
