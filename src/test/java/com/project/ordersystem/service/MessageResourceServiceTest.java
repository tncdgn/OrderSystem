package com.project.ordersystem.service;

import com.project.ordersystem.dao.MessageResourceDao;
import com.project.ordersystem.entity.MessageResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageResourceServiceTest {

    @InjectMocks
    private MessageResourceService messageResourceService;

    @Mock
    private MessageResourceDao messageResourceDao;

    private Map<String, String> messageResourcesMap = new HashMap<>();

    @Test
    public void shouldSave() {
        MessageResource messageResource = MessageResource.builder().build();
        messageResourceService.save(messageResource);

        verify(messageResourceDao).save(messageResource);
    }

    @Test
    public void shouldFindById() {
        messageResourceService.findById(1l);

        verify(messageResourceDao).findById(1l);
    }

    @Test
    public void shouldInit() {
        MessageResource messageResource = MessageResource.builder().content("content").key("key").build();
        when(messageResourceDao.findAll()).thenReturn(Arrays.asList(messageResource));

        messageResourceService.init();

        verify(messageResourceDao).findAll();
    }

    @Test
    public void shouldGetMessageByKey() {
        assertNull(messageResourceService.getMessage("key"));
    }
}