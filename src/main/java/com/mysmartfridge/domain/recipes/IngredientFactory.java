package com.mysmartfridge.domain.recipes;

import java.text.Normalizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.components.recipes.ProductsRepository;
import com.mysmartfridge.domain.products.Product;
import com.mysmartfridge.domain.recipes.Quantity.Unit;

/**
 * Factory used to transform raw information coming from the user into an
 * {@link Ingredient} object useful in the model.
 */
@Service
public class IngredientFactory {

	/**
	 * Repository for {@link Product} objects.
	 */
	@Autowired
	private ProductsRepository productsRepository;

	/**
	 * Builds an ingredient based on raw data.
	 * 
	 * @param quantity
	 *            the quantity of this ingredient present in the recipe.
	 * @param unitInput
	 *            The unit the quantity is expressed in.
	 * @param product
	 *            name of the product that constitutes this ingredient. The name
	 *            must correspond to a product in database.
	 */
	public Ingredient build(Double quantity, String unitInput, String productName) {

		Product p = parseProduct(productName);

		Unit unit = null;
		for (Unit u : Unit.class.getEnumConstants()) {
			if (u.name().equals(unitInput)) {
				unit = u;
			}
		}

		Quantity q = new Quantity(quantity, unit);
		Ingredient ret = new Ingredient(q, p);

		return ret;
	}

	/**
	 * Find the product by a name that may be approximate
	 */
	private Product parseProduct(String input) {
		
		// Replace accents with base character
		String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFD).toLowerCase();
		normalizedInput = normalizedInput.replaceAll("\\p{M}", "");
		
		Product outProduct = null;
		for (Product p : productsRepository.findAll()) {
			if (normalizedInput.equals(p.getName().toLowerCase())) {
				outProduct = p;
			}
		}

		return outProduct;
	}
}
