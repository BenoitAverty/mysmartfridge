package com.mysmartfridge.domain.recipes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class Recipe implements Serializable {

	private static final long serialVersionUID = -3895773238629033452L;

	@Id
	@Getter
	private UUID uuid;

	@Getter
	private String title;

	@Getter
	private int nbPeople;

	@Getter
	private int prepTime;

	@Getter
	private int cookTime;

	@Getter
	private List<Ingredient> ingredients;

	private List<Step> steps;

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
		this.ingredients.add(ingredient);
	}

	/**
	 * Add several {@link Ingredient}s to this recipe.
	 * 
	 * @param ingredients
	 *            the ingredients to add
	 */
	public void addIngredients(Collection<Ingredient> ingredients) {
		for (Ingredient i : ingredients) {
			this.addIngredient(i);
		}
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
}
