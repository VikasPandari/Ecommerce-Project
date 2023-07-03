package com.rest.cjss.repository;

import com.rest.cjss.entity.ProductEntity;
import com.rest.cjss.entity.ProductSkusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSkusRepository extends JpaRepository<ProductSkusEntity, String> {
    Optional<ProductSkusEntity> findBySkuCode(String skuCode);
}
