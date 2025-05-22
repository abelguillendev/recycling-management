package com.aguillen.recycling.management.service;

import java.util.List;

import com.aguillen.recycling.management.entity.Product;

public interface ProductService {

	Product create(Product product);
	
	Product getById(Long id);
	
	Product update(Long id, Product updatedProduct);
	
	List<Product> getAll();
	
	Product delete(Long id);
}
