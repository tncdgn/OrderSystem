package com.project.ordersystem.service;

import com.project.ordersystem.dao.OrderDao;
import com.project.ordersystem.entity.*;
import com.project.ordersystem.entity.generator.EntityGeneratorModel;
import com.project.ordersystem.model.CreateOrderModel;
import com.project.ordersystem.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService extends BaseService<Order> {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderDao.findById(id);
    }

    @Transactional
    public void createOrder(CreateOrderModel createOrderModel) {
        List<Product> products = productService.findOrThrowException(createOrderModel.generateIdListFromProductModels());
        validateProductsStock(products, createOrderModel.getProductModels());
        EntityGeneratorModel entityGeneratorModel = generateEntityGeneratorModel(createOrderModel);
        List<OrderItem> orderItems = productOrderItemGenerator.generateProductOrderItem(entityGeneratorModel, products);
        orderItemService.saveBulk(orderItems);
    }

    private void validateProductsStock(List<Product> products, List<ProductModel> productModels) {
        products.forEach(product -> productValidator.validateStock(product, productModels));
    }

    private EntityGeneratorModel generateEntityGeneratorModel(CreateOrderModel createOrderModel) {
        Buyer buyer = buyerService.findBuyerOrThrowException(createOrderModel.getBuyerId());
        WareHouseAddress wareHouseAddress = wareHouseAddressService.findOrThrowException(createOrderModel.getBuyerWareHouseAddressId());
        EntityGeneratorModel entityGeneratorModel = EntityGeneratorModel.builder().productModels(createOrderModel.getProductModels()).buyer(buyer).wareHouseAddress(wareHouseAddress).build();

        return entityGeneratorModel;
    }
}
