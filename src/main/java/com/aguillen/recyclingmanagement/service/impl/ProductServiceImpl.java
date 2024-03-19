package com.aguillen.recyclingmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguillen.recyclingmanagement.entity.Product;
import com.aguillen.recyclingmanagement.repository.ProductRepository;
import com.aguillen.recyclingmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public Product create(Product product) {
		return repository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Product update(Long id, Product updatedProduct) {
		Optional<Product> productoExistente = repository.findById(id);
        if (productoExistente.isPresent()) {
        	Product product = productoExistente.get();
        	product.setCode(updatedProduct.getCode());
        	product.setName(updatedProduct.getName());
        	product.setDescription(updatedProduct.getDescription());
        	product.setPricePerKG(updatedProduct.getPricePerKG());
            return repository.save(product);
        }
        return null;
	}

	@Override
	public List<Product> getAll() {
		return repository.findAll();
	}

	@Override
	public Product delete(Long id) {
		Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isPresent()) {
        	repository.deleteById(id);
        	return existingProduct.get();
        } else {
        	return null;
        }
	}

}
