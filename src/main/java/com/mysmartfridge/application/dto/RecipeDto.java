package com.mysmartfridge.application.dto;

import com.mysmartfridge.domain.Recipe;

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
		this.instructions = recipe.getInstructions();
	}

	/** tid of the recipe. This attribute represents the identity of a recipe. */
	public long tid;
	
	/** Title of the recipe. */
	public String title;
	
	/** Html instructions of the recipe. */
	public String instructions;
}
