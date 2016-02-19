package com.mysmartfridge.domain.products;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Long> {
	
	public Collection<Product> findAll();
}
