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
	
	def "getIngredients method should not allow addition or deletion of an ingredient through the returned reference"() {
		
		when: 'Adding an ingredient to the list returned by getIngredients() method'
			recipeUnderTest.getIngredients().add(Stub(Ingredient))
		then: 'The ingredient is not added to the ingredients of the recipe.'
			recipeUnderTest.getIngredients().size() == 0
		
		when: 'Removing an ingredient from the list returned by getIngredients() method'
			recipeUnderTest.addIngredient(Stub(Ingredient))
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
	
	def "addStep() method should keep the order of the steps in the recipe"() {
		given: 'Two steps'
			String step1 = "Step1"
			String step2 = "Step2"
			String step3 = "Step3"
		
		when: 'Adding the steps to the recipe'
			recipeUnderTest.addStep(step1)
			recipeUnderTest.addStep(step2)
			recipeUnderTest.addStep(step3)
		then: 'getSteps() method returns the two steps in the right order'
			recipeUnderTest.getSteps().size() == 2
			recipeUnderTest.getSteps().get(0).equals(step1)
			recipeUnderTest.getSteps().get(1).equals(step2)
			recipeUnderTest.getSteps().get(0).equals(step3)
		
	}
	
	def "getSteps method should not allow addition or deletion of a step through the returned reference"() {
		
		when: 'Adding a step to the list returned by getIngredients() method'
			recipeUnderTest.getSteps().add("test")
		then: 'The step is not added to the steps of the recipe.'
			recipeUnderTest.getSteps().size() == 0
		
		when: 'Removing an ingredient from the list returned by getIngredients() method'
			recipeUnderTest.addStep("test")
			recipeUnderTest.getSteps().remove(0)
		then: 'The ingredient is not removed from the ingredients of the recipe'
			recipeUnderTest.getSteps().size() == 1
		
	}
}
