package com.project.ordersystem.dao;

import com.project.ordersystem.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDao extends JpaRepository<Seller, Long> {
}
