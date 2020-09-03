package com.project.ordersystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static java.util.TimeZone.getDefault;

@Data
public class CreateOrderModel extends BaseModel {
    private long buyerId;
    private List<ProductModel> productModels;
    private long buyerWareHouseAddressId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss",timezone = "GMT+3" )
    private Date orderDate;

    public List<Long> generateIdListFromProductModels() {
        return productModels.stream().map(productModel -> productModel.getProductId()).collect(Collectors.toList());
    }
}
