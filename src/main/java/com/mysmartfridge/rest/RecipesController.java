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
public class RecipesController {

	/**
	 * Application for the recipes.
	 */
	@Autowired
	RecipesApplication recipeApp;
	
	@RequestMapping(value="/recipes/{tid}", method=RequestMethod.GET)
	public String getRecipes(@PathVariable("tid") long tid) {
		return recipeApp.findARecipe(tid);
	}
	
	@RequestMapping(value="/recipes", method=RequestMethod.POST)
	public RecipeDto postRecipes(@RequestBody RecipeDto dto) {
		return recipeApp.createRecipe(dto);
	}
}
