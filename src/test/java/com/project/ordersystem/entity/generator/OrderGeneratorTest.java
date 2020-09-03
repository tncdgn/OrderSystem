package com.project.ordersystem.entity.generator;

import com.project.ordersystem.entity.Buyer;
import com.project.ordersystem.entity.Order;
import com.project.ordersystem.entity.WareHouseAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderGeneratorTest {

    @InjectMocks
    private OrderGenerator orderGenerator;

    @Test
    public void shouldGenerateOrder() {
        Buyer buyer = Buyer.builder().id(1l).companyName("migros").email("mail@mail.com").build();
        WareHouseAddress wareHouseAddress = WareHouseAddress.builder().address("address").city("istanbul").postCode("31900").build();
        Date date = new Date();

        EntityGeneratorModel entityGeneratorModel = EntityGeneratorModel.builder().buyer(buyer).wareHouseAddress(wareHouseAddress).orderDate(date).build();
        Order order = orderGenerator.generate(entityGeneratorModel);

        assertEquals(order.getBuyer(), buyer);
        assertEquals(order.getWareHouseAddress(), wareHouseAddress);
        assertEquals(order.getOrderDate(), date);
    }

    @Test
    public void shouldCalculateTotalPrice() {
        Order order = new Order();

        orderGenerator.addProductPriceToTotalCost(order, BigDecimal.TEN);

        assertEquals(order.getTotalPrice(), BigDecimal.TEN);
    }
}