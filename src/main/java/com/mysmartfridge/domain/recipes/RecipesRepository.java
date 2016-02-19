package com.mysmartfridge.domain.recipes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepository extends CrudRepository<Recipe, Long> {
}
