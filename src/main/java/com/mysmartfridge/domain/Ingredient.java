package com.mysmartfridge.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

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
@Entity
@Table(name="ingredients")
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = -1040899094370022361L;

	@Column
	@Getter
	private String quantity;
	
	@ManyToOne
	@JoinColumn(name="recipe_tid")
	@Id
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="product_tid")
	@Id
	private Product product;
	
	public String toString() {
		return this.quantity + " " + this.product.getName();
	}
}
