package com.project.ordersystem.dao;

import com.project.ordersystem.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerDao extends JpaRepository<Buyer, Long> {
}
