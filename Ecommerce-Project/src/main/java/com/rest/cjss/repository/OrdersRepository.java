package com.rest.cjss.repository;

import com.rest.cjss.entity.ProductOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<ProductOrdersEntity,Integer> {
}
