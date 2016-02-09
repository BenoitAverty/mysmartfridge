package com.mysmartfridge.application;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.application.dto.RecipeDto;
import com.mysmartfridge.domain.Recipe;
import com.mysmartfridge.domain.repositories.RecipeRepository;

@Service
public class RecipesApplication {

	@Autowired
	private RecipeRepository recipeRepo;
	
	/**
	 * Find a recipe based on its tid
	 * 
	 * @param tid the tid of the recipe to find
	 * @return a RecipeDto representing the found recipe
	 */
	public RecipeDto findARecipe(long tid) {
		return new RecipeDto(recipeRepo.findOne(tid));
	}

	/**
	 * Create a recipe and save it in database.
	 * 
	 * @param recipeDto the recipe to create.
	 * @return a RecipeDto representing the created recipe.
	 */
	public RecipeDto createRecipe(RecipeDto recipeDto) {

		Recipe newRecipe = new Recipe(recipeDto.title, recipeDto.nbPeople, recipeDto.prepTime, recipeDto.cookTime);
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
