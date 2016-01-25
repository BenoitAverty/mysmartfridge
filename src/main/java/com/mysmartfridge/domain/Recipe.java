package com.mysmartfridge.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Recipes")
@NoArgsConstructor
public class Recipe implements Serializable {

	private static final long serialVersionUID = -3895773238629033452L;

	@Id
	@Getter
	private long tid;
	
	@Column
	@Type(type="text")
	private String instructions;
	
	public Recipe(String instructions) {
		this.instructions = instructions;
	}
	
	public String toString() {
		return this.tid + " : " + this.instructions;
	}
}
