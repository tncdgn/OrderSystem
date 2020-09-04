package com.project.ordersystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductModel extends BaseModel {
    private Long productId;
    private int quantity;

    public int generateProductIdAsInt() {
        return productId.intValue();
    }
}
