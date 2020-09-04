package com.project.ordersystem.service;

import com.project.ordersystem.dao.SellerDao;
import com.project.ordersystem.entity.Seller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceTest {

    @InjectMocks
    private SellerService sellerService;

    @Mock
    private SellerDao sellerDao;

    @Test
    public void shouldSave() {
        Seller seller = Seller.builder().build();
        sellerService.save(seller);

        verify(sellerDao).save(seller);
    }

    @Test
    public void shouldFindById() {
        sellerService.findById(1l);

        verify(sellerDao).findById(1l);
    }
}