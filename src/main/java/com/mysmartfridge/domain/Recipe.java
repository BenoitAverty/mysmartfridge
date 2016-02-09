package com.mysmartfridge.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@OneToMany(mappedBy="recipe")
	private Set<Ingredient> ingredients;

	@OneToMany(mappedBy="recipe")
	private Set<Step> steps;

	public Recipe(String title, int nbPeople, int prepTime, int cookTime) {
		this.title = title;
		this.nbPeople = nbPeople;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
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

	/**
	 * Return the ingredients of the recipe as strings.
	 */
	public List<String> getIngredients() {
		return ingredients.stream().map(i -> i.toString()).collect(Collectors.toList());
	}
}
