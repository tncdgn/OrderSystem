package com.project.ordersystem.service;

import com.project.ordersystem.dao.MessageResourceDao;
import com.project.ordersystem.entity.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MessageResourceService extends BaseService<MessageResource> {

    @Autowired
    private MessageResourceDao messageResourceDao;

    private Map<String, String> messageResourcesMap = new HashMap<>();

    @Override
    public void save(MessageResource messageResource) {
        messageResourceDao.save(messageResource);
    }

    @Override
    public Optional<MessageResource> findById(long id) {
        return messageResourceDao.findById(id);
    }

    public void init() {
        prepareMessageMap(messageResourceDao.findAll());
    }

    public String getMessage(String key) {
        return messageResourcesMap.get(key);
    }

    private void prepareMessageMap(List<MessageResource> messageResources) {
        messageResources.forEach(messageResource -> {
            messageResourcesMap.put(messageResource.getKey(), messageResource.getContent());
        });
    }
}
