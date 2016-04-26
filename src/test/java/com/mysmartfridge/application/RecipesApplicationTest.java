package com.mysmartfridge.application;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import com.mysmartfridge.components.recipes.ProductsRepository;
import com.mysmartfridge.components.recipes.RecipeDto;
import com.mysmartfridge.components.recipes.RecipesApplication;
import com.mysmartfridge.components.recipes.RecipesRepository;
import com.mysmartfridge.domain.recipes.Recipe;

@SpringApplicationConfiguration(classes = RecipesApplicationTest.class) // This test class will configure its own context
@ComponentScan("com.mysmartfridge.domain.recipes") // Scan recipes domain package so that domain services are available
@Import(RecipesApplication.class) // Load the bean that we want to test.
public class RecipesApplicationTest {
	
	@ClassRule
	public static final SpringClassRule SCR = new SpringClassRule();
	
	@Rule
	public final SpringMethodRule SMR = new SpringMethodRule();
	
	@Autowired
	private RecipesRepository recipesRepositoryMock;
	
	@Autowired
	private ProductsRepository productsRepositoryMock;
	
	// Get the service to test from the context
	@Autowired
	RecipesApplication recipesApplication;
	
	@Before
	public void beforeEach() {
		// Need to reset mocks before they are in the spring application context and cannot be dumped and recreated
		Mockito.reset(productsRepositoryMock);
		Mockito.reset(recipesRepositoryMock);
	}
	
	@Test
	public void findRandomRecipeReturnsNullWhenCollectionEmpty() {
		//given
		Mockito.when(recipesRepositoryMock.findAll()).thenReturn(new ArrayList<>());
		
		//when
		RecipeDto recipe = recipesApplication.findRandomRecipe();
		
		//then
		Assertions.assertThat(recipe).isNull();
	}
	
	@Test
	public void addingARecipeShouldMakeItAvailableInRandomRecipes() {
		//given
		RecipeDto dto = new RecipeDto();
		dto.title="test";
		dto.ingredients = new ArrayList<>();
		dto.steps = new ArrayList<>();
		
		final List<Recipe> recipesInMock = new ArrayList<>();
		
		Mockito.when(recipesRepositoryMock.save(Mockito.any(Recipe.class))).thenAnswer(new Answer<Recipe>() {
			@Override
			public Recipe answer(InvocationOnMock aInvocation) throws Throwable {
				Recipe arg = aInvocation.getArgumentAt(0, Recipe.class);
	            recipesInMock.add(arg);
	            return arg;
			}
		});
		
		Mockito.when(recipesRepositoryMock.findAll()).thenAnswer(new Answer<List<Recipe>>() {
			@Override
			public List<Recipe> answer(InvocationOnMock aInvocation) throws Throwable {
	            return recipesInMock;
			}
		});
		
		
		//when
		dto = recipesApplication.createRecipe(dto);
		RecipeDto randomDto = recipesApplication.findRandomRecipe();
		
		//then
		Assertions.assertThat(randomDto).isEqualTo(dto);
	}
	
	// Inject the recipeRepository mock in the context
	@Bean
	RecipesRepository recipesRepository() {
		return Mockito.mock(RecipesRepository.class);
	}
	
	//inject the productsRepository mock in the context
	@Bean
	ProductsRepository productsRepository() {
		return Mockito.mock(ProductsRepository.class);
	}
	
}
;