package com.mysmartfridge.domain.products;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Product, Long> {
	
	public List<Product> findAll();
}
