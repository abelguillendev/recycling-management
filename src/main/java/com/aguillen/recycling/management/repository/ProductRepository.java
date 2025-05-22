package com.aguillen.recycling.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aguillen.recycling.management.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
