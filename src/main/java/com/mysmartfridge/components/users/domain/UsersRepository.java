package com.mysmartfridge.components.users.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, UUID> {
	
	/**
	 * Find a User by its email address.
	 * 
	 * @param email the address to look for.
	 * @return A user with the given address (or Optional.empty if not found).
	 */
	public Optional<User> findByEmail(String email);
}
