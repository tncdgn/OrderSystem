package com.project.ordersystem.entity.generator;

import com.project.ordersystem.entity.*;
import com.project.ordersystem.model.ProductModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
@Builder
public class EntityGeneratorModel {
    private Product product;
    private Buyer buyer;
    private Order order;
    private WareHouseAddress wareHouseAddress;
    private List<ProductModel> productModels;
    private Set<OrderItem> productOrderItems;
}
