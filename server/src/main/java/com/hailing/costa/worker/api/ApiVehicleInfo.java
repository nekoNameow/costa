package com.hailing.costa.worker.api;

import com.hailing.costa.entity.VehicleEntity;

public class ApiVehicleInfo {
    private String msidn;
    private String engineStatus;
    private String fleet;
    private String brand;
    private String countryOfOperation;
    private String chassisNumber;
    private String cassisSeries;

    public static String url = "/info";

    public VehicleEntity getEntity() {
        VehicleEntity entity = new VehicleEntity();
        entity.setMsidn(msidn);
        entity.setEngineStatus(engineStatus);
        entity.setFleet(fleet);
        entity.setBrand(brand);
        entity.setCountryOfOperation(countryOfOperation);
        entity.setChassisNumber(chassisNumber);
        entity.setCassisSeries(cassisSeries);
        return entity;
    }
}
