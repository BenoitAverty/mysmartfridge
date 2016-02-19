package com.mysmartfridge.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.mysmartfridge.domain.recipes.Recipe;

import lombok.NoArgsConstructor;

/**
 * Represents a recipe as exposed by the application layer.
 */
@NoArgsConstructor
public class RecipeDto {
	
	/**
	 * Create a DTO based on the domain Recipe object.
	 * @param recipe the domain Recipe the dto will be based on.
	 */
	public RecipeDto(Recipe recipe) {
		this.tid = recipe.getTid();
		this.title = recipe.getTitle();
		this.nbPeople = recipe.getNbPeople();
		this.prepTime = recipe.getPrepTime();
		this.cookTime = recipe.getCookTime();
		this.ingredients = recipe.getIngredients().stream().map(i -> i.toString()).collect(Collectors.toList());
		this.steps = recipe.getSteps();
	}

	/** tid of the recipe. This attribute represents the identity of a recipe. */
	public long tid;
	
	/** Title of the recipe. */
	public String title;
	
	/** How many people the recipe is for. */
	public int nbPeople;
	
	/** Time in minutes required to prepare the recipe. */
	public int prepTime;
	
	/** Time in minutes needed to cook the recipe. */
	public int cookTime;
	
	/** Ingredients. */
	public List<String> ingredients;
	
	/** Html instructions of the recipe. */
	public List<String> steps;
}
