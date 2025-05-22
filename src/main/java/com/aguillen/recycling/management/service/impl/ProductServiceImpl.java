package com.aguillen.recycling.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.aguillen.recycling.management.entity.Product;
import com.aguillen.recycling.management.repository.ProductRepository;
import com.aguillen.recycling.management.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

    /**
     * Creates a new product in the database.
     *
     * @param product The product to be created.
     * @return The created product.
     * @throws DataAccessException If there is an error during the creation process.
     */
	@Override
	public Product create(Product product) {
		try {
			return repository.save(product);
		} catch (DataAccessException e) {
			throw new RuntimeException("Error creating product", e);
		}
	}

    /**
     * Retrieves a product by its ID from the database.
     *
     * @param id The ID of the product to be retrieved.
     * @return The retrieved product, or null if no product was found with the given ID.
     * @throws DataAccessException If there is an error during the retrieval process.
     */
	@Override
	public Product getById(Long id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (DataAccessException e) {
			throw new RuntimeException("Error retrieving product", e);
		}
	}

    /**
     * Updates a product in the database.
     *
     * @param id The ID of the product to be updated.
     * @param updatedProduct The product data to be updated.
     * @return The updated product, or null if no product was found with the given ID.
     * @throws DataAccessException If there is an error during the update process.
     */
	@Override
	public Product update(Long id, Product updatedProduct) {
		try {
			Optional<Product> existingProduct = repository.findById(id);
			if (existingProduct.isPresent()) {
				Product product = existingProduct.get();
				product.setCode(updatedProduct.getCode());
				product.setName(updatedProduct.getName());
				product.setDescription(updatedProduct.getDescription());
				product.setPricePerKG(updatedProduct.getPricePerKG());
				return repository.save(product);
			}
			return null;
		} catch (DataAccessException e) {
			throw new RuntimeException("Error updating product", e);
		}
	}

    /**
     * Retrieves all products from the database.
     *
     * @return A list of all products.
     * @throws DataAccessException If there is an error during the retrieval process.
     */
	@Override
	public List<Product> getAll() {
		try {
			return repository.findAll();
		} catch (DataAccessException e) {
			throw new RuntimeException("Error retrieving all products", e);
		}
	}

    /**
     * Deletes a product by its ID from the database.
     *
     * @param id The ID of the product to be deleted.
     * @return The deleted product, or null if no product was found with the given ID.
     * @throws DataAccessException If there is an error during the deletion process.
     */
	@Override
	public Product delete(Long id) {
		try {
			Optional<Product> existingProduct = repository.findById(id);
			if (existingProduct.isPresent()) {
				repository.deleteById(id);
				return existingProduct.get();
			} else {
				return null;
			}
		} catch (DataAccessException e) {
			throw new RuntimeException("Error deleting product", e);
		}
	}
}
