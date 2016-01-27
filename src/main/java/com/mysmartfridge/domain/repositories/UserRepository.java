package com.mysmartfridge.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mysmartfridge.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * Find a User by its email address.
	 * 
	 * @param email the address to look for.
	 * @return A user with the given address or Null if not found.
	 */
	public User findByEmail(String email);
}
