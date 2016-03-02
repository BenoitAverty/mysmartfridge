package com.mysmartfridge.domain.recipes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepository extends MongoRepository<Recipe, Long> {
}
