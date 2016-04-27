package com.mysmartfridge.components.recipes.domain;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface RecipesRepository extends MongoRepository<Recipe, UUID> {
}
