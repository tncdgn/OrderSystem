package com.project.ordersystem.dao;

import com.project.ordersystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.id in :ids")
    List<Product> findByIdList(@Param("ids") List<Long> ids);
}
