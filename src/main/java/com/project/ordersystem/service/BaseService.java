package com.project.ordersystem.service;

import com.project.ordersystem.entity.BaseEntity;
import com.project.ordersystem.entity.generator.OrderGenerator;
import com.project.ordersystem.entity.generator.ProductOrderItemGenerator;
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
    protected ProductOrderItemGenerator productOrderItemGenerator;

    @Autowired
    protected OrderGenerator orderGenerator;

    @Autowired
    protected OrderItemService orderItemService;

    public abstract void save(T t);

    public abstract Optional<T> findById(long id);

    public void saveBulk(List<T> tList) {
        tList.forEach(t -> {
            save(t);
        });
    }
}
