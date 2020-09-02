package com.project.ordersystem.dao;

import com.project.ordersystem.entity.MessageResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageResourceDao extends JpaRepository<MessageResource, Long> {
}
