package com.mysmartfridge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

/**
 * This entity represents a product that can be used in a recipe.
 * 
 * It can be anything edible !
 *
 */
@Entity
@Table(name="products")
public class Product {

	/**
	 * tid of the product.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long tid;
	
	/**
	 * Name of the product/
	 */
	@Column
	@Getter
	private String name;
}
