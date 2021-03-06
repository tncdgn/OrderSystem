package com.project.ordersystem.service;

import com.project.ordersystem.dao.BuyerDao;
import com.project.ordersystem.entity.Buyer;
import com.project.ordersystem.exception.BuyerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService extends BaseService<Buyer> {

    @Autowired
    private BuyerDao buyerDao;

    @Override
    public void save(Buyer buyer) {
        buyerDao.save(buyer);
    }

    @Override
    public Optional<Buyer> findById(long id) {
        return buyerDao.findById(id);
    }

    public Buyer findBuyerOrThrowException(long id) {
        return findById(id).orElseThrow(() -> new BuyerNotFoundException("buyer.notFound"));
    }
}
