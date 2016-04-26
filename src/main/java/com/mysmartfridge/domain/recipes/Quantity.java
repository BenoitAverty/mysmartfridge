package com.mysmartfridge.domain.recipes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Represent a quantity of some product.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Quantity {
	/** How much there is */
	private final Double value;
	
	/** The unit this quantity is in. */
	private final Unit unit;
	
	/**
	 * Represent the units handled by the application.
	 */
	public enum Unit {
		GRAM, 
		KILOGRAM, 
		LITER,
		MILLILITER, 
		NO_UNIT, 
		TABLESPOON, 
		CUP;
	}
}
