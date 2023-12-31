package com.rest.cjss.repository;

import com.rest.cjss.entity.ProductEntity;
import com.rest.cjss.entity.ProductSkusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


    }
