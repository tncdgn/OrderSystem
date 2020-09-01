package com.project.ordersystem.service;

import com.project.ordersystem.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity> {

    public abstract void save(T t);

    public abstract Optional<T> findById(long id);

    public void saveBulk(List<T> tList) {
        tList.forEach(t -> {
            save(t);
        });
    }
}
