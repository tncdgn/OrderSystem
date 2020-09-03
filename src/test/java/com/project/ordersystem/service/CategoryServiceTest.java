package com.project.ordersystem.service;

import com.project.ordersystem.dao.CategoryDao;
import com.project.ordersystem.entity.Buyer;
import com.project.ordersystem.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryDao categoryDao;

    @Test
    public void shouldSave() {
        Category category = Category.builder().build();
        categoryService.save(category);

        verify(categoryDao).save(category);
    }

    @Test
    public void shouldFindById() {
        categoryService.findById(1l);

        verify(categoryDao).findById(1l);
    }
}