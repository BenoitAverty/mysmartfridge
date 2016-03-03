package com.mysmartfridge.domain.products;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This entity represents a product that can be used in a recipe.
 * 
 * It can be anything edible !
 */
@Document(collection="products")
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class Product {

	/**
	 * tid of the product.
	 */
	@Id
	private Integer tid;
	
	/**
	 * Name of the product/
	 */
	@Getter
	private String name;
	
	/**
	 * Constructs of a product by name.
	 */
	public Product(String n) {
		name = n;
	}
}
