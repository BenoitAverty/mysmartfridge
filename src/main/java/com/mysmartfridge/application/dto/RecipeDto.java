package com.mysmartfridge.application.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mysmartfridge.domain.recipes.Ingredient;
import com.mysmartfridge.domain.recipes.Recipe;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a recipe as exposed by the application layer.
 */
@NoArgsConstructor
@EqualsAndHashCode
public class RecipeDto {
	
	/**
	 * Create a DTO based on the domain Recipe object.
	 * @param recipe the domain Recipe the dto will be based on.
	 */
	public RecipeDto(Recipe recipe) {
		this.uuid = recipe.getUuid();
		this.title = recipe.getTitle();
		this.nbPeople = recipe.getNbPeople();
		this.prepTime = recipe.getPrepTime();
		this.cookTime = recipe.getCookTime();
		this.ingredients = recipe.getIngredients().stream().map(IngredientDto::new).collect(Collectors.toList());
		this.steps = recipe.getSteps();
	}

	/** uuid of the recipe. This attribute represents the identity of a recipe. */
	public UUID uuid;
	
	/** Title of the recipe. */
	public String title;
	
	/** How many people the recipe is for. */
	public int nbPeople;
	
	/** Time in minutes required to prepare the recipe. */
	public int prepTime;
	
	/** Time in minutes needed to cook the recipe. */
	public int cookTime;
	
	/** Ingredients. */
	public List<IngredientDto> ingredients;
	
	/** Html instructions of the recipe. */
	public List<String> steps;
	
	/**
	 * Dto for the ingredients of the recipe.
	 */
	@NoArgsConstructor
	@EqualsAndHashCode
	public static class IngredientDto {
		
		public IngredientDto(Ingredient i) {
			this.quantity = i.getQuantity().getValue();
			this.unit = i.getQuantity().getUnit().name();
			this.product = i.getProduct().getName();
		}
		
		/** Quantity of the ingredient. */
		public Double quantity;
		
		/** Unit the quantity is in. */
		public String unit;
		
		/** Name of the product that's in the recipe. */
		public String product;
	}
}
