package com.project.ordersystem.service;

import com.project.ordersystem.dao.WareHouseAddressDao;
import com.project.ordersystem.entity.WareHouseAddress;
import com.project.ordersystem.exception.WareHouseAddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WareHouseAddressService extends BaseService<WareHouseAddress> {

    @Autowired
    private WareHouseAddressDao wareHouseAddressDao;

    @Override
    public void save(WareHouseAddress wareHouseAddress) {
        wareHouseAddressDao.save(wareHouseAddress);
    }

    @Override
    public Optional<WareHouseAddress> findById(long id) {
        return wareHouseAddressDao.findById(id);
    }

    public WareHouseAddress findOrThrowException(long id) {
        return findById(id).orElseThrow(() -> new WareHouseAddressNotFoundException("warehouse.notFound"));
    }
}
