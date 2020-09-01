package com.project.ordersystem.service;

import com.project.ordersystem.dao.SellerDao;
import com.project.ordersystem.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService extends BaseService<Seller> {

    @Autowired
    private SellerDao sellerDao;

    @Override
    public void save(Seller seller) {
        sellerDao.save(seller);
    }

    @Override
    public Optional<Seller> findById(long id) {
        return sellerDao.findById(id);
    }
}
