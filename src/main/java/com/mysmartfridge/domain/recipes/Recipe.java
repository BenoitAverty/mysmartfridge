package com.mysmartfridge.domain.recipes;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Entity
@Table(name = "Recipes")
@NoArgsConstructor
public class Recipe implements Serializable {

	private static final long serialVersionUID = -3895773238629033452L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tid;

	@Column
	@Getter
	private String title;

	@Column
	@Getter
	private int nbPeople;

	@Column
	@Getter
	private int prepTime;

	@Column
	@Getter
	private int cookTime;

	@ElementCollection(targetClass=Ingredient.class)
	@JoinTable(name="ingredients")
	@JoinColumn(name="recipe_tid", referencedColumnName="tid")
	@Getter
	private Set<Ingredient> ingredients;

	@OneToMany(mappedBy="recipe")
	private Set<Step> steps;

	/**
	 * Create a recipe with specified parameters and no ingredients nor steps.
	 * 
	 * @param title The name of the recipe
	 * @param nbPeople The number of people the recipe is designed for
	 * @param prepTime The time needed to prepare the recipe
	 * @param cookTime The time needed in the oven or on the stove to cook the recipe.
	 */
	public Recipe(String title, int nbPeople, int prepTime, int cookTime) {
		this.title = title;
		this.nbPeople = nbPeople;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		
		this.ingredients = new HashSet<>();
		this.steps = new HashSet<>();
	}
	
	/**
	 * Add an {@link Ingredient} to this recipe.
	 * 
	 * @param ingredient the ingredient to add
	 */
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	
	/**
	 * Add several {@link Ingredient}s to this recipe.
	 * 
	 * @param ingredients the ingredients to add
	 */
	public void addIngredients(Collection<Ingredient> ingredients) {
		for(Ingredient i : ingredients) {
			this.addIngredient(i);
		}
	}

	/**
	 * Return the steps of the recipe as an iterable.
	 * 
	 * The steps are sorted according to their order attribute.
	 */
	public List<String> getSteps() {

		return steps.stream().sorted(
					(s1, s2) -> Integer.compare(s1.getIndex(), s2.getIndex())).map(s -> s.getText()
				)
				.collect(Collectors.toList());

	}
}
