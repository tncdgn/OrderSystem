package com.project.ordersystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductModel extends BaseModel {
    private Long productId;
    private int quantity;

    @JsonIgnore
    public int generateProductIdAsInt() {
        return productId.intValue();
    }
}
