package com.project.ordersystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreateOrderModel extends BaseModel {
    private long buyerId;
    private List<ProductModel> productModels;
    private long buyerWareHouseAddressId;


    public List<Long> generateIdListFromProductModels() {
        return productModels.stream().map(productModel -> productModel.getProductId()).collect(Collectors.toList());
    }
}
