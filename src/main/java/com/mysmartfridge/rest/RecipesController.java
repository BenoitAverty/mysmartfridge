package com.mysmartfridge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartfridge.Utils;
import com.mysmartfridge.application.RecipesApplication;
import com.mysmartfridge.application.dto.RecipeDto;

/**
 * Endpoint for the Recipe resource.
 * 
 * The Recipe resource is equivalent to the {@link RecipeDto} class exposed by the application layer. No mapping is done at the controller layer.
 */
@RestController
@RequestMapping(value="/api/recipes")
public class RecipesController {

	/**
	 * Application layer for the recipes.
	 */
	@Autowired
	RecipesApplication recipeApp;
	
	/** 
	 * Get a particular recipe from its tid.
	 */
	@RequestMapping(value="/{tid}", method=RequestMethod.GET)
	public ResponseEntity<RecipeDto> getRecipesTid(@PathVariable("tid") long tid) {
		return new ResponseEntity<RecipeDto>(recipeApp.findARecipe(tid), HttpStatus.OK);
	}
	
	/**
	 * Create a recipe from the json equivalent of a {@link RecipeDto}.
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> postRecipes(@RequestBody RecipeDto dto) {
		
		RecipeDto created = recipeApp.createRecipe(dto);
		
		return ResponseEntity.created(Utils.buildUriForTid(created.tid)).build();
		
	}
	
	/**
	 * Get a random recipe.
	 */
	@RequestMapping(value="/random", method=RequestMethod.GET)
	public ResponseEntity<RecipeDto> getRecipesRandom() {
		return new ResponseEntity<RecipeDto>(recipeApp.findRandomRecipe(), HttpStatus.OK);
	}
}
