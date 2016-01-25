package com.mysmartfridge.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.domain.Recipe;
import com.mysmartfridge.domain.RecipeRepository;

@Service
public class RecipeApplication {

	@Autowired
	private RecipeRepository recipeRepo;
	
	public String findARecipe(long tid) {
		return recipeRepo.findOne(tid).toString();
	}

	public long createRecipe(String body) {

		Recipe newRecipe = new Recipe(body);
		recipeRepo.save(newRecipe);
		return newRecipe.getTid();
	}

}
