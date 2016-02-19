package com.mysmartfridge.domain.recipes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.mysmartfridge.domain.products.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This represents an ingredient used in a recipe.
 * 
 * Although this class is an Entity for spring and hibernate, it is NOT an entity conceptually, but a value object. Thus:
 * <ul>
 * 	<li>The class doesn't have a tid (not needed as it will only be retrieved through a recipe)</li>
 *  <li>It is immutable, so it can be safely shared between several recipes</li>
 * </ul>
 *
 */
@Embeddable
@NoArgsConstructor
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = -1040899094370022361L;

	@Getter
	private Quantity quantity;
	
	@ManyToOne
	@JoinColumn(name="product_tid")
	@Getter
	private Product product;
	
	public Ingredient(Quantity q, Product p) {
		quantity = q;
		product = p;
	}
	
	public String toString() {
		return this.quantity + " " + this.product.getName();
	}
}
