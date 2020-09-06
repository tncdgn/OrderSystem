package com.project.ordersystem.service;

import com.project.ordersystem.dao.OrderDao;
import com.project.ordersystem.entity.*;
import com.project.ordersystem.entity.generator.EntityGeneratorFactory;
import com.project.ordersystem.entity.generator.EntityGeneratorModel;
import com.project.ordersystem.entity.generator.ProductOrderItemGenerator;
import com.project.ordersystem.model.CreateOrderModel;
import com.project.ordersystem.model.ProductModel;
import com.project.ordersystem.validator.ProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.project.ordersystem.entity.generator.EntityGeneratorType.PRODUCT_ORDER_ITEM;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @Mock
    private BuyerService buyerService;

    @Mock
    private WareHouseAddressService wareHouseAddressService;

    @Mock
    private ProductOrderItemGenerator productOrderItemGenerator;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    protected ProductValidator productValidator;

    @Mock
    private OrderDao orderDao;

    @Mock
    private EntityGeneratorFactory entityGeneratorFactory;

    @Test
    public void shouldSave() {
        Order order = new Order();
        orderService.save(order);

        verify(orderDao).save(order);
    }

    @Test
    public void shouldFindById() {
        orderService.findById(1l);

        verify(orderDao).findById(1l);
    }

    @Test
    public void shouldCreateOrder() {
        CreateOrderModel createOrderModel = new CreateOrderModel();
        ProductModel productModel = ProductModel.builder().quantity(2).productId(1L).build();

        createOrderModel.setProductModels(Arrays.asList(productModel));

        Seller seller = new Seller();
        Product product = Product.builder().id(1l).preparationTime(10).seller(seller).price(BigDecimal.TEN).title("Title1").stock(12).build();

        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productService.findOrThrowException(Arrays.asList(1l))).thenReturn(products);

        createOrderModel.setBuyerId(2l);
        Buyer buyer = Buyer.builder().id(1l).companyName("migros").email("mail@mail.com").build();
        when(buyerService.findBuyerOrThrowException(2l)).thenReturn(buyer);

        createOrderModel.setBuyerWareHouseAddressId(123l);

        WareHouseAddress wareHouseAddress = WareHouseAddress.builder().address("address").city("istanbul").postCode("31900").build();
        when(wareHouseAddressService.findOrThrowException(123l)).thenReturn(wareHouseAddress);

        EntityGeneratorModel entityGeneratorModel = EntityGeneratorModel.builder().orderDate(createOrderModel.getOrderDate()).productModels(createOrderModel.getProductModels()).buyer(buyer).wareHouseAddress(wareHouseAddress).build();

        OrderItem orderItem = new ProductOrderItem();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        when(entityGeneratorFactory.get(PRODUCT_ORDER_ITEM)).thenReturn(productOrderItemGenerator);
        when(productOrderItemGenerator.generateProductOrderItem(entityGeneratorModel, products)).thenReturn(orderItems);

        orderService.createOrder(createOrderModel);

        verify(orderItemService).saveBulk(orderItems);
        verify(productValidator).validateStock(product, Arrays.asList(productModel));
    }
}