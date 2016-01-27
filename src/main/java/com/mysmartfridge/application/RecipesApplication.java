package com.mysmartfridge.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.application.dto.RecipeDto;
import com.mysmartfridge.domain.Recipe;
import com.mysmartfridge.domain.repositories.RecipeRepository;

@Service
public class RecipesApplication {

	@Autowired
	private RecipeRepository recipeRepo;
	
	public String findARecipe(long tid) {
		return recipeRepo.findOne(tid).toString();
	}

	/**
	 * Create a recipe and save it in database.
	 * 
	 * @param recipeDto the recipe to create.
	 * @return a RecipeDto representing the created recipe.
	 */
	public RecipeDto createRecipe(RecipeDto recipeDto) {

		Recipe newRecipe = new Recipe(recipeDto.title, recipeDto.instructions);
		recipeRepo.save(newRecipe);
		
		return new RecipeDto(newRecipe);
	}

}
