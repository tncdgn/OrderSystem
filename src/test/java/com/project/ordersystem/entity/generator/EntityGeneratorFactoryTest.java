package com.project.ordersystem.entity.generator;

import org.junit.Test;

import java.util.Arrays;

import static com.project.ordersystem.entity.generator.EntityGeneratorType.PRODUCT_ORDER_ITEM;
import static org.junit.jupiter.api.Assertions.*;

public class EntityGeneratorFactoryTest {

    @Test
    public void shouldGetType() {
        OrderGenerator orderGenerator = new OrderGenerator();
        ProductOrderItemGenerator productOrderItemGenerator = new ProductOrderItemGenerator();

        EntityGeneratorFactory entityGeneratorFactory = new EntityGeneratorFactory(Arrays.asList(orderGenerator, productOrderItemGenerator));

        assertEquals(entityGeneratorFactory.get(PRODUCT_ORDER_ITEM), productOrderItemGenerator);
    }
}