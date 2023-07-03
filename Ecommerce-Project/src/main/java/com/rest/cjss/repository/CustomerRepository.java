package com.rest.cjss.repository;

import com.rest.cjss.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    Optional<CustomerEntity> findByEmail(String email);
}
