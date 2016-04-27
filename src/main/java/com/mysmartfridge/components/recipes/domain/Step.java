package com.mysmartfridge.components.recipes.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Value object representing a single step in a recipe.
 * 
 * As this is a value object, it is immutable and has no tid.
 *
 */
@Document(collection="steps")
@AllArgsConstructor
class Step {
	
	@Getter
	private final int index;
	
	@Getter
	private final String text;
}