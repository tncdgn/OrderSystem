package com.project.ordersystem.dao;

import com.project.ordersystem.entity.WareHouseAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseAddressDao extends JpaRepository<WareHouseAddress, Long> {
}
