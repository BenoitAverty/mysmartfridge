package com.mysmartfridge.components.recipes;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mysmartfridge.domain.recipes.Recipe;

@Repository
public interface RecipesRepository extends MongoRepository<Recipe, UUID> {
}
