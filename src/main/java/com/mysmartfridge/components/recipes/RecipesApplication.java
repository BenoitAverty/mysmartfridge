package com.mysmartfridge.components.recipes;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.domain.recipes.IngredientFactory;
import com.mysmartfridge.domain.recipes.Recipe;

/**
 * Application layer related to Recipes.
 * 
 * The application layer exposes results and takes argument from the
 * {@link com.mysmartfridge.application.dto} package. In this particular case,
 * mostly the {@link RecipeDto} class.
 */
@Service
public class RecipesApplication {
	
	/**
	 * Repository for the {@link Recipe} entity (and its aggregate : Ingredients, Quantities, Units).
	 */
	@Autowired
	private RecipesRepository recipeRepo;
	
	@Autowired
	private IngredientFactory ingredientFactory;

	/**
	 * Find a recipe based on its tid
	 * 
	 * @param uuid
	 *            the uuid of the recipe to find
	 * @return a RecipeDto representing the found recipe
	 */
	public RecipeDto findARecipe(UUID uuid) {
		return new RecipeDto(recipeRepo.findOne(uuid));
	}

	/**
	 * Create a recipe and save it in database.
	 * 
	 * @param recipeDto
	 *            the recipe to create.
	 * @return a RecipeDto representing the created recipe.
	 */
	public RecipeDto createRecipe(RecipeDto recipeDto) {

		Recipe newRecipe = new Recipe(recipeDto.title, recipeDto.nbPeople, recipeDto.prepTime, recipeDto.cookTime);

		recipeDto.ingredients.stream()
			.map(dto -> ingredientFactory.build(dto.quantity, dto.unit, dto.product))
			.forEach(newRecipe::addIngredient);
		
		recipeDto.steps.stream()
			.forEach(newRecipe::addStep);

		recipeRepo.save(newRecipe);

		return new RecipeDto(newRecipe);
	}

	/**
	 * Find a random recipe in database.
	 */
	public RecipeDto findRandomRecipe() {

		List<Recipe> allRecipes = recipeRepo.findAll();

		SecureRandom rand = new SecureRandom();
		
		return allRecipes.isEmpty() ? null : new RecipeDto(allRecipes.get(rand.nextInt(allRecipes.size())));
	}

}
