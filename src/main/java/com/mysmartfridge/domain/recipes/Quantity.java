package com.mysmartfridge.domain.recipes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represent a quantity of some product.
 */
@Getter
@AllArgsConstructor
public class Quantity {
	/** How much there is */
	private Double value;
	
	/** The unit this quantity is in. */
	private Unit unit;
	
	public String toString() {
		return value + unit.getLabel();
	}
	
	/**
	 * Represent the units handled by the application.
	 */
	public enum Unit {
		GRAM("g", "g", "gramme", "grammes"), 
		KILOGRAM("kg", "kg", "kilo", "kilos", "kilogramme", "kilogrammes"), 
		LITER("l", "l", "litre", "litres"),
		MILLILITER("ml", "ml", "millilitres", "millilitre"), 
		NO_UNIT("", ""), 
		TABLESPOON("cuillère à café", "cuillères à café"), 
		CUP("verre", "verres");
		
		private Unit(String label, String pluralLabel, String... altLabels) {
			this.label = label;
			this.pluralLabel = pluralLabel;
			this.altLabels = new HashSet<String>(Arrays.asList(altLabels));
		}
		
		/** The label of this unit as written in the recipes. */
		@Getter
		private String label;
		
		/** Label written in the recipes if the quantity is plural. */
		@Getter
		private String pluralLabel;
		
		/** Alternative labels that can be used to recognize the unit. */
		@Getter
		private Set<String> altLabels;
	}
}
