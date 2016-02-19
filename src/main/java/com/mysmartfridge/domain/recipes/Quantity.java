package com.mysmartfridge.domain.recipes;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represent a quantity of some product.
 */
@Getter
@AllArgsConstructor
public class Quantity {
	private Unit unit;
	private Double value;
	
	@AllArgsConstructor
	public enum Unit {
		GRAM("g"), KILOGRAM("kg"), LITER("l"), MILLILITER("ml"), NO_UNIT(""), TABLESPOON("cuillères à café"), CUP("verres");
		
		@Getter
		private String label;
	}
	
	public String toString() {
		return value + unit.getLabel();
	}
}
