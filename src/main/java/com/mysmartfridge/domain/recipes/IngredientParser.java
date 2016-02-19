package com.mysmartfridge.domain.recipes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.domain.products.Product;
import com.mysmartfridge.domain.products.ProductsRepository;
import com.mysmartfridge.domain.recipes.Quantity.Unit;

/**
 * Service used to parse ingredients as strings coming from user input and
 * construct an {@link Ingredient} object useful in the model.
 */
@Service
public class IngredientParser {
	
	@Autowired
	private ProductsRepository productsRepository;
	
	private Set<Product> allProducts;
	
	@PostConstruct
	private void init() {
		allProducts = new HashSet<Product>(productsRepository.findAll());
	}
	
	public Ingredient parse(String input) {
		
		Product outProduct = null;
		for(Product p : allProducts) {
			if(input.toLowerCase().contains(p.getName().toLowerCase())) {
				outProduct = p;
			}
		}
		
		Quantity q = new Quantity(Unit.GRAM, new Random().nextDouble());
		Ingredient ret = new Ingredient(q, outProduct);
		
		return ret;
	}
}
