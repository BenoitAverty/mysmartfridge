package com.mysmartfridge.domain.recipe

import com.mysmartfridge.domain.products.Product
import com.mysmartfridge.domain.recipes.DuplicateIngredientException;
import com.mysmartfridge.domain.recipes.Ingredient
import com.mysmartfridge.domain.recipes.Quantity
import com.mysmartfridge.domain.recipes.Quantity.Unit;
import com.mysmartfridge.domain.recipes.Recipe;

import spock.lang.Specification;

class RecipeSpec extends Specification {
	
	private Recipe recipeUnderTest = new Recipe("Test", 4, 20, 20)
	
	def "getIngredients method should not allow addition/deletion of an ingredient through the returned reference"() {
		
		when: 'Adding an ingredient to the list returned by getIngredients() method'
			recipeUnderTest.getIngredients().add(new Ingredient())
		then: 'The ingredient is not added to the ingredients of the recipe.'
			recipeUnderTest.getIngredients().size() == 0
		
		when: 'Removing an ingredient from the list returned by getIngredients() method'
			recipeUnderTest.addIngredient(new Ingredient())
			recipeUnderTest.getIngredients().remove(0)
		then: 'The ingredient is not removed from the ingredients of the recipe'
			recipeUnderTest.getIngredients().size() == 1
		
	}
	
	def "It should not be possible to add two ingredients with the same product to a recipe"() {
		given: 'Two ingredients that return the same product'
			Product p = new Product("test")
			Ingredient i1 = new Ingredient(new Quantity(1, Unit.NO_UNIT), p)
			Ingredient i2 = new Ingredient(new Quantity(2, Unit.NO_UNIT), p)
		when: 'Adding both ingredients to the recipe'
			recipeUnderTest.addIngredient(i1)
			recipeUnderTest.addIngredient(i2)
		then: 'An exception is thrown and the second ingredient is not added'
			recipeUnderTest.getIngredients().size() == 1
			thrown(DuplicateIngredientException)
	}
}
