package com.project.ordersystem.service;

import com.project.ordersystem.dao.ProductDao;
import com.project.ordersystem.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService extends BaseService<Product> {

    @Autowired
    private ProductDao productDao;

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productDao.findById(id);
    }
}
