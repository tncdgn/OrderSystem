package com.project.ordersystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreateOrderModel extends BaseModel {
    private long buyerId;
    private List<ProductModel> productModels = new ArrayList<>();
    private long buyerWareHouseAddressId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT+3")
    private Date orderDate;

    public List<Long> generateIdListFromProductModels() {
        return productModels.stream().map(productModel -> productModel.getProductId()).collect(Collectors.toList());
    }
}
