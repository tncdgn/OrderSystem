package com.project.ordersystem.entity.generator;

import com.project.ordersystem.entity.*;
import com.project.ordersystem.model.ProductModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.project.ordersystem.entity.generator.EntityGeneratorType.PRODUCT_ORDER_ITEM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductOrderItemGeneratorTest {

    @InjectMocks
    private ProductOrderItemGenerator productOrderItemGenerator;

    @Mock
    private OrderGenerator orderGenerator;

    @Test
    public void shouldGenerateProductOrderItem() {
        Seller seller = new Seller();

        Product product1 = Product.builder().id(1l).preparationTime(10).seller(seller).price(BigDecimal.TEN).title("Title1").stock(12).build();

        ProductModel productModel = ProductModel.builder().productId(1l).quantity(2).build();

        Buyer buyer = Buyer.builder().id(1l).companyName("migros").email("mail@mail.com").build();
        WareHouseAddress wareHouseAddress = WareHouseAddress.builder().address("address").city("istanbul").postCode("31900").build();
        Date date = new Date();

        EntityGeneratorModel entityGeneratorModel = EntityGeneratorModel.builder().productModels(Arrays.asList(productModel)).buyer(buyer).wareHouseAddress(wareHouseAddress).orderDate(date).build();
        Order order = new Order();

        when(orderGenerator.generate(entityGeneratorModel)).thenReturn(order);

        List<OrderItem> orderItems = productOrderItemGenerator.generateProductOrderItem(entityGeneratorModel, Arrays.asList(product1));

        assertEquals(orderItems.size(), 1);
        OrderItem orderItem = orderItems.get(0);

        assertEquals(orderItem.getPreparationTime(), 10);
        assertEquals(orderItem.getSeller(), seller);
        assertEquals(orderItem.getPrice(), BigDecimal.valueOf(20));
        assertEquals(orderItem.getProduct(), product1);
        assertEquals(orderItem.getProductTitle(), product1.getTitle());
        assertEquals(orderItem.getQuantity(), 2);
        assertEquals(orderItem.getOrder(), order);
    }

    @Test
    public void shouldGetType() {
        assertEquals(productOrderItemGenerator.type(), PRODUCT_ORDER_ITEM);
    }
}