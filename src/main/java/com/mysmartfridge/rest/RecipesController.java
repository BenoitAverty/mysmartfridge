package com.mysmartfridge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartfridge.application.RecipesApplication;
import com.mysmartfridge.application.dto.RecipeDto;

@RestController
@RequestMapping(value="/api/recipes")
public class RecipesController {

	/**
	 * Application for the recipes.
	 */
	@Autowired
	RecipesApplication recipeApp;
	
	@RequestMapping(value="/{tid}", method=RequestMethod.GET)
	public RecipeDto getRecipes(@PathVariable("tid") long tid) {
		return recipeApp.findARecipe(tid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public RecipeDto postRecipes(@RequestBody RecipeDto dto) {
		return recipeApp.createRecipe(dto);
	}
	
	@RequestMapping(value="/random", method=RequestMethod.GET)
	public RecipeDto getRecipesRandom() {
		return recipeApp.findRandomRecipe();
	}
}
