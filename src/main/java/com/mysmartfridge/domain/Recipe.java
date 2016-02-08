package com.mysmartfridge.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Recipes")
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
	private String ingredients;
	
	@Column
	@Getter
	private String steps;
	
	public Recipe(String title, String steps) {
		this.title = title;
		this.steps = steps;
	}
	
	public String toString() {
		return "[" + this.tid + "] " + this.title + " : " + this.steps;
	}
}
