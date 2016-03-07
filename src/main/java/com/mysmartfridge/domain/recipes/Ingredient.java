package com.mysmartfridge.domain.recipes;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mysmartfridge.domain.products.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This represents an ingredient that can be used in a recipe.
 * 
 * An ingredient represents some quantity of some product.
 * 
 * Value Object: 
 * <ul>
 * 	<li>Immutable (setters are not present)</li>
 * 	<li>No identity (No need to find an ingredient by itself, always through recipe)</li>
 * </ul>
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@AllArgsConstructor
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = -1040899094370022361L;
	
	/** 
	 * Quantity of the product in the recipe.
	 */
	@Getter
	private Quantity quantity;
	
	/**
	 * The product present in the recipe.
	 */
	@Getter
	@DBRef
	private Product product;
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return this.quantity + " " + this.product.getName();
	}
}
