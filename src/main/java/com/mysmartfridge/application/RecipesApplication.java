package com.mysmartfridge.application;

import java.security.SecureRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.application.dto.RecipeDto;
import com.mysmartfridge.domain.recipes.IngredientFactory;
import com.mysmartfridge.domain.recipes.Recipe;
import com.mysmartfridge.domain.recipes.RecipesRepository;

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
	
	/**
	 * Domain service used to parse Ingredients from string inputs into domain objects.
	 */
	@Autowired
	private IngredientFactory ingredientParser;

	/**
	 * Find a recipe based on its tid
	 * 
	 * @param tid
	 *            the tid of the recipe to find
	 * @return a RecipeDto representing the found recipe
	 */
	public RecipeDto findARecipe(long tid) {
		return new RecipeDto(recipeRepo.findOne(tid));
	}

	/**
	 * Create a recipe and save it in database.
	 * 
	 * @param recipeDto
	 *            the recipe to create.
	 * @return a RecipeDto representing the created recipe.
	 */
	@Transactional
	public RecipeDto createRecipe(RecipeDto recipeDto) {

		Recipe newRecipe = new Recipe(recipeDto.title, recipeDto.nbPeople, recipeDto.prepTime, recipeDto.cookTime);

		recipeDto.ingredients.stream()
			.map(dto -> ingredientParser.build(dto.quantity, dto.unit, dto.product))
			.forEach(newRecipe::addIngredient);

		recipeRepo.save(newRecipe);

		return new RecipeDto(newRecipe);
	}

	/**
	 * Find a random recipe in database.
	 */
	public RecipeDto findRandomRecipe() {

		Iterable<Recipe> allRecipes = recipeRepo.findAll();

		SecureRandom rand = new SecureRandom();
		double min = 1.0;
		Recipe chosenRecipe = null;
		for (Recipe recipe : allRecipes) {
			double cur = rand.nextDouble();
			if (cur < min) {
				min = cur;
				chosenRecipe = recipe;
			}
		}

		return new RecipeDto(chosenRecipe);
	}

}
