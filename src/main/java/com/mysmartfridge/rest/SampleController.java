package com.mysmartfridge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartfridge.application.RecipeApplication;

@RestController
public class SampleController {

	@Autowired
	RecipeApplication recipeApp;
	
	@RequestMapping(value="/recipes/{tid}", method=RequestMethod.GET)
	public String getRecipes(@PathVariable("tid") long tid) {
		return recipeApp.findARecipe(tid);
	}
	
	@RequestMapping(value="/recipes", method=RequestMethod.POST)
	public long postRecipes(@RequestBody String body) {
		return recipeApp.createRecipe(body);
	}
	
	
}
