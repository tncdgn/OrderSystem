package com.project.ordersystem.service;

import com.project.ordersystem.dao.ProductDao;
import com.project.ordersystem.entity.Product;
import com.project.ordersystem.exception.ProductNotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Product> findOrThrowException(List<Long> ids) {
        List<Product> products = productDao.findByIdList(ids);

        if (CollectionUtils.isEmpty(products) || assertNotEquals(products.size(), ids.size())) {
            throw new ProductNotFoundException("product.notFound");
        }

        return products;
    }

    private boolean assertNotEquals(int foundProductSize, int givenProductSize) {
        return foundProductSize != givenProductSize;
    }
}
