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

@RestController
@RequestMapping(value="/api/recipes")
public class RecipesController {

	/**
	 * Application for the recipes.
	 */
	@Autowired
	RecipesApplication recipeApp;
	
	@RequestMapping(value="/{tid}", method=RequestMethod.GET)
	public ResponseEntity<RecipeDto> getRecipes(@PathVariable("tid") long tid) {
		return new ResponseEntity<RecipeDto>(recipeApp.findARecipe(tid), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> postRecipes(@RequestBody RecipeDto dto) {
		
		RecipeDto created = recipeApp.createRecipe(dto);
		
		return ResponseEntity.created(Utils.buildUriForTid(created.tid)).build();
		
	}
	
	@RequestMapping(value="/random", method=RequestMethod.GET)
	public ResponseEntity<RecipeDto> getRecipesRandom() {
		return new ResponseEntity<RecipeDto>(recipeApp.findRandomRecipe(), HttpStatus.OK);
	}
}
