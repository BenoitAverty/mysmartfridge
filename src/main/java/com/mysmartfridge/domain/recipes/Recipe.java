package com.mysmartfridge.domain.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysmartfridge.domain.Entity;
import com.mysmartfridge.domain.products.Product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity representing a recipe managed by the system.
 * 
 * A recipe is composed of several basic attributes (title, preparation
 * time...), a list of ingredients, and a list of steps to follow to reproduce
 * the recipe.
 * 
 */
@Document(collection = "recipes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe extends Entity {


	/**
	 * Create a recipe with specified parameters and no ingredients nor steps.
	 * 
	 * @param title
	 *            The name of the recipe
	 * @param nbPeople
	 *            The number of people the recipe is designed for
	 * @param prepTime
	 *            The time needed to prepare the recipe
	 * @param cookTime
	 *            The time needed in the oven or on the stove to cook the
	 *            recipe.
	 */
	public Recipe(String title, int nbPeople, int prepTime, int cookTime) {
		this.title = title;
		this.nbPeople = nbPeople;
		this.prepTime = prepTime;
		this.cookTime = cookTime;

		this.ingredients = new ArrayList<>();
		this.steps = new ArrayList<>();
	}

	/**
	 * Add an {@link Ingredient} to this recipe.
	 * 
	 * @param ingredient
	 *            the ingredient to add
	 */
	public void addIngredient(Ingredient ingredient) {
		
		for(Ingredient i : this.ingredients) {
			if(i.getProduct().isSame(ingredient.getProduct())) {
				throw new DuplicateIngredientException();
			}
		}
		
		this.ingredients.add(ingredient);
	}
	
	/**
	 * Add a step to this recipe.
	 */
	public void addStep(String stepText) {
	}

	/**
	 * Return the list of steps of the recipe.
	 * 
	 * The steps are sorted according to their index attribute.
	 */
	public List<String> getSteps() {

		return steps.stream()
				.sorted((s1, s2) -> Integer.compare(s1.getIndex(), s2.getIndex()))
				.map(s -> s.getText())
				.collect(Collectors.toList());

	}

	/**
	 * Return the list of ingredients of the recipe.
	 * 
	 * The list is returned as a defensive copy of the ingredients list, so the
	 * ingredients cannot be modified through the returned reference of this
	 * method. Instead, use the appropriate method (e.g. :
	 * {@link #addIngredient(Ingredient) or #addIngredients(Collection)}.
	 */
	public List<Ingredient> getIngredients() {
		return new ArrayList<>(ingredients);
	}
	
	// Private members.
	
	@Getter
	private String title;

	@Getter
	private int nbPeople;

	@Getter
	private int prepTime;

	@Getter
	private int cookTime;
	
	private List<Ingredient> ingredients;

	private List<Step> steps;


}
