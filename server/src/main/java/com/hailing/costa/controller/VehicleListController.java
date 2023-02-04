package com.hailing.costa.controller;

import java.util.List;

import com.hailing.costa.dao.VehicleDao;
import com.hailing.costa.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VehicleListController {
    @Autowired
    private VehicleDao vehicleDao;

    @ResponseBody
    @RequestMapping(path = "/vehicle/list", method = RequestMethod.GET)
    public List<VehicleEntity> main() {
        return this.vehicleDao.getList();
    }
}