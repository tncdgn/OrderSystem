package com.project.ordersystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DataLoaderServiceTest {

    @InjectMocks
    private DataLoaderService dataLoaderService;

    @Mock
    private BuyerService buyerService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private SellerService sellerService;

    @Mock
    private ProductService productService;

    @Mock
    private WareHouseAddressService wareHouseAddressService;

    @Mock
    private MessageResourceService messageResourceService;

    @Test
    public void shouldLoadData() {
        dataLoaderService.prepareData();

        verify(buyerService).saveBulk(anyList());
        verify(categoryService).saveBulk(anyList());
        verify(sellerService).saveBulk(anyList());
        verify(productService).saveBulk(anyList());
        verify(wareHouseAddressService).saveBulk(anyList());
        verify(messageResourceService).saveBulk(anyList());

        verify(messageResourceService).init();
    }
}