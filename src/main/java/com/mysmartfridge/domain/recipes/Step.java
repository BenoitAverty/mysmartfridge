package com.mysmartfridge.domain.recipes;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

/**
 * Value object representing a single step in a recipe.
 * 
 * As this is a value object, it is immutable and has no tid.
 *
 */
@Document(collection="steps")
public class Step implements Serializable {
	
	private static final long serialVersionUID = 7932211446922316915L;
	
	@Getter
	private int index;
	
	@Getter
	private String text;
}
