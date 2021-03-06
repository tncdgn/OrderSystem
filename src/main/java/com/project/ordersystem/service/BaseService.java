package com.project.ordersystem.service;

import com.project.ordersystem.entity.BaseEntity;
import com.project.ordersystem.entity.generator.EntityGeneratorFactory;
import com.project.ordersystem.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity> {

    @Autowired
    protected BuyerService buyerService;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected WareHouseAddressService wareHouseAddressService;

    @Autowired
    protected ProductValidator productValidator;

    @Autowired
    protected OrderItemService orderItemService;

    @Autowired
    protected EntityGeneratorFactory entityGeneratorFactory;

    public abstract void save(T t);

    public abstract Optional<T> findById(long id);

    public void saveBulk(List<T> tList) {
        tList.forEach(t -> {
            save(t);
        });
    }
}
