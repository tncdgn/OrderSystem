package com.project.ordersystem.service;

import com.project.ordersystem.dao.CategoryDao;
import com.project.ordersystem.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService extends BaseService<Category> {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryDao.findById(id);
    }
}
