package com.mysmartfridge.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

/**
 * A user of the application.
 */
@Entity
@Table(name="Users")
public class User implements Serializable {
	
	private static final long serialVersionUID = -8067114967162203161L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tid;
	
	@Column
	@Getter
	private String email;
	
	@Column
	@Getter
	private String password;
	
}
