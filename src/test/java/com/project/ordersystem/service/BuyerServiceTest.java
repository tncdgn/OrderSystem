package com.project.ordersystem.service;

import com.project.ordersystem.dao.BuyerDao;
import com.project.ordersystem.entity.Buyer;
import com.project.ordersystem.exception.BuyerNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BuyerServiceTest {

    @InjectMocks
    private BuyerService buyerService;

    @Mock
    private BuyerDao buyerDao;

    @Test
    public void shouldSave() {
        Buyer buyer = Buyer.builder().build();
        buyerService.save(buyer);

        verify(buyerDao).save(buyer);
    }

    @Test
    public void shouldFindById() {
        buyerService.findById(1l);

        verify(buyerDao).findById(1l);
    }

    @Test(expected = BuyerNotFoundException.class)
    public void shouldThrowExceptionIfBuyerNotFindById() {
        when(buyerDao.findById(1l)).thenReturn(Optional.empty());

        buyerService.findBuyerOrThrowException(1l);
    }

    @Test
    public void shouldSaveBulk() {
        Buyer buyer = Buyer.builder().build();
        Buyer buyer1 = Buyer.builder().build();

        buyerService.saveBulk(Arrays.asList(buyer, buyer1));

        verify(buyerDao, times(2)).save(any(Buyer.class));
    }
}