package com.mysmartfridge.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

/**
 * Value object representing a single step in a recipe.
 * 
 * As this is a value object, it is immutable and has no tid.
 *
 */
@Entity
@Table(name="steps")
public class Step implements Serializable {
	
	private static final long serialVersionUID = 7932211446922316915L;

	@ManyToOne
	@Id
	private Recipe recipe;
	
	@Column
	@Getter
	@Id
	private int index;
	
	@Column
	@Getter
	private String text;
}
