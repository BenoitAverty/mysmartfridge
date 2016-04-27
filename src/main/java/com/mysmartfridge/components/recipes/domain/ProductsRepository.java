package com.mysmartfridge.components.recipes.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Product, UUID> {
	
	public List<Product> findAll();
}
