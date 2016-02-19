package com.mysmartfridge.domain.recipes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.domain.products.Product;
import com.mysmartfridge.domain.products.ProductsRepository;
import com.mysmartfridge.domain.recipes.Quantity.Unit;

/**
 * Domain service used to parse ingredients as strings coming from user input and
 * construct an {@link Ingredient} object useful in the model.
 */
@Service
public class IngredientParser {
	
	/**
	 * Repository for {@link Product} objects.
	 */
	@Autowired
	private ProductsRepository productsRepository;
	
	/**
	 * Parse a string into an ingredient.
	 * 
	 * <ul>
	 * 	<li>Iterate on all products until one is contained in the string: that's the product of this ingredient.</li>
	 * 	<li>Parse a number at the beginning of the string, that's the quantity value.</li>
	 * 	<li>A group of letter after the quantity value will be matched against a unit.</li>
	 * </ul>
	 */
	public Ingredient parse(String input) {
		
		Product p = parseProduct(input);
		
		Integer productStartsAt = input.toLowerCase().indexOf(p.getName().toLowerCase());
		input = input.substring(0, productStartsAt);
		
		// Pattern matching for the quantity
		Pattern quantityValuePattern = Pattern.compile("([0-9]*\\.?[0-9]+)\\s?(.+)");
		Matcher m = quantityValuePattern.matcher(input);
		Double value = 0.0;
		Unit unit = Unit.NO_UNIT;
		while(m.find()) {
			String foundNumber = m.group(1);
			String foundUnit = m.group(2);
			value = Double.parseDouble(foundNumber);
			unit = parseUnit(foundUnit);
			
		}
		
		Quantity q = new Quantity(value, unit);
		Ingredient ret = new Ingredient(q, p);
		
		return ret;
	}
	
	/**
	 * Guesses the unit from an input string.
	 */
	private Unit parseUnit(String input) {
		
		for(Unit u : Unit.class.getEnumConstants()) {
			
			Boolean foundLabel = input.contains(u.getLabel());
			Boolean foundPluralLabel = input.contains(u.getPluralLabel());
			Boolean foundAltLabel = u.getAltLabels().stream()
					.map(l -> input.contains(l))
					.reduce(false, Boolean::logicalOr);
			
			if(foundLabel || foundPluralLabel || foundAltLabel)
			{
				return u;
			}
		}
		
		return Unit.NO_UNIT;
	}
	
	/**
	 * Find the product of an entire ingredient string 
	 */
	private Product parseProduct(String input)  {
		Product outProduct = null;
		for(Product p : productsRepository.findAll()) {
			if(input.toLowerCase().contains(p.getName().toLowerCase())) {
				outProduct = p;
			}
		}
		
		return outProduct;
	}
}
