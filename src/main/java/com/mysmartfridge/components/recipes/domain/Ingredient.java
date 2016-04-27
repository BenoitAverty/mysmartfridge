package com.mysmartfridge.components.recipes.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@AllArgsConstructor
public class Ingredient {
	
	/** 
	 * Quantity of the product in the recipe.
	 */
	@Getter
	private final Quantity quantity;
	
	/**
	 * The product present in the recipe.
	 */
	@Getter
	@DBRef
	private final Product product;
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return this.quantity + " " + this.product.getName();
	}
}