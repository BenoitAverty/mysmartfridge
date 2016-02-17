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
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = -8067114967162203161L;

	/**
	 * Identity of the user in the system.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tid;

	/**
	 * Email address of the user.
	 */
	@Column
	@Getter
	private String email;

	/**
	 * Hashed password of the user.
	 */
	@Column
	@Getter
	private String password;

	/**
	 * Role of the user in the application. Is used to determine rights and do
	 * some processes differently regarding the role.
	 */
	@Column
	@Getter
	private Role role;
}
