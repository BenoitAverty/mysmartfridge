package com.mysmartfridge.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mysmartfridge.domain.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
	
	/**
	 * Find a User by its email address.
	 * 
	 * @param email the address to look for.
	 * @return A user with the given address (or Optional.empty if not found).
	 */
	public Optional<User> findByEmail(String email);
}
