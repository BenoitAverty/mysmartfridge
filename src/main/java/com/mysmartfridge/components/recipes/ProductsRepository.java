package com.mysmartfridge.components.recipes;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mysmartfridge.domain.products.Product;

public interface ProductsRepository extends MongoRepository<Product, UUID> {
	
	public List<Product> findAll();
}
