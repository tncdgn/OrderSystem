package com.project.ordersystem.service;

import com.project.ordersystem.dao.WareHouseAddressDao;
import com.project.ordersystem.entity.Seller;
import com.project.ordersystem.entity.WareHouseAddress;
import com.project.ordersystem.exception.BuyerNotFoundException;
import com.project.ordersystem.exception.WareHouseAddressNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WareHouseAddressServiceTest {

    @InjectMocks
    private WareHouseAddressService wareHouseAddressService;

    @Mock
    private WareHouseAddressDao wareHouseAddressDao;

    @Test
    public void shouldSave() {
        WareHouseAddress wareHouseAddress = WareHouseAddress.builder().build();
        wareHouseAddressService.save(wareHouseAddress);

        verify(wareHouseAddressDao).save(wareHouseAddress);
    }

    @Test
    public void shouldFindById() {
        wareHouseAddressService.findById(1l);

        verify(wareHouseAddressDao).findById(1l);
    }

    @Test(expected = WareHouseAddressNotFoundException.class)
    public void shouldThrowExceptionIfBuyerNotFindById() {
        when(wareHouseAddressDao.findById(1l)).thenReturn(Optional.empty());

        wareHouseAddressService.findOrThrowException(1l);
    }
}