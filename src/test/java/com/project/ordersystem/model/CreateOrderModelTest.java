package com.project.ordersystem.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateOrderModelTest {

    @Test
    public void shouldGenerateIdListFromProductModels() {
        ProductModel productModel1 = ProductModel.builder().productId(1l).quantity(2).build();
        ProductModel productModel2 = ProductModel.builder().productId(2l).quantity(3).build();

        CreateOrderModel createOrderModel = new CreateOrderModel();
        createOrderModel.setProductModels(Arrays.asList(productModel1, productModel2));

        List<Long> idList = createOrderModel.generateIdListFromProductModels();

        assertEquals(idList.size(), 2);

        assertTrue(idList.contains(1l));
        assertTrue(idList.contains(2l));
    }
}