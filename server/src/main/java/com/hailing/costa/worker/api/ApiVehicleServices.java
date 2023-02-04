package com.hailing.costa.worker.api;

import com.hailing.costa.entity.ServiceEntity;

public class ApiVehicleServices {
    private String communicationStatus;
    private ServiceEntity[] services;

    public static String url = "/services";

    public ServiceEntity[] getServices() {
        if (!this.communicationStatus.equals("ACTIVE") || this.services == null) {
            return new ServiceEntity[0];
        }
        return this.services;
    }
}
