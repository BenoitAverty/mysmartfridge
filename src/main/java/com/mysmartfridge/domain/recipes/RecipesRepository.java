package com.mysmartfridge.domain.recipes;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepository extends MongoRepository<Recipe, UUID> {
}
