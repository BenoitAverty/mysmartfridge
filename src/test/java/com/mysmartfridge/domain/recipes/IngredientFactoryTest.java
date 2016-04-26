package com.mysmartfridge.domain.recipes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mysmartfridge.components.recipes.ProductsRepository;
import com.mysmartfridge.domain.products.Product;
import com.mysmartfridge.domain.recipes.Quantity.Unit;
import com.nitorcreations.junit.runners.NestedRunner;

/**
 * Test for the domain factory {@link IngredientFactory}.
 */
@RunWith(NestedRunner.class)
public class IngredientFactoryTest {
	
	/**
	 * System under test.
	 * 
	 * @see IngredientFactory
	 */
	@InjectMocks
	IngredientFactory parser;
	
	/**
	 * Mock products repository.
	 * 
	 * @see ProductsRepository
	 */
	@Mock
	ProductsRepository productsRepo;
	
	@Before
	public void beforeEach() {
		parser = new IngredientFactory();
		MockitoAnnotations.initMocks(this);
	}
	
	public class buildMethod {
		
		@SuppressWarnings("serial")
		List<Product> prds = new ArrayList<Product>() {{
			add(new Product("Lait"));
			add(new Product("Oeufs"));
			add(new Product("Creme"));
			add(new Product("Farine"));
			add(new Product("Sucre glace"));
			add(new Product("Sucre"));
		}};
		
		@Before
		public void beforeEach() {
			when(productsRepo.findAll()).thenReturn(prds);
		}
		
		public class withASimpleInput {
			
			/** value of the quantity expected in output **/
			private Double quantityValue = 220.0;
			private Integer expectedProductIndex = 3;
			private Unit expectedUnit = Unit.GRAM;
			private String inputProduct = "Farine";
			private String inputUnit = "GRAM";
					
			@Test
			public void shouldReturnIngredientWithCorrectProduct() {
				Ingredient g = parser.build(quantityValue, inputUnit, inputProduct);
				
				assertThat(g.getProduct().getName()).isEqualTo(prds.get(expectedProductIndex).getName());
			}
			
			@Test
			public void shouldReturnQuantityWithCorrectValue() {
				Ingredient g = parser.build(quantityValue, inputUnit, inputProduct);
				
				assertThat(g.getQuantity().getValue()).isEqualTo(quantityValue);
			}
			
			@Test
			public void shouldReturnQuantityWithCorrectUnit() {
				Ingredient g = parser.build(quantityValue, inputUnit, inputProduct);
				
				assertThat(g.getQuantity().getUnit()).isEqualTo(expectedUnit);
			}
		}
		
		public class productGuessing {
			
			@Test
			public void shouldBeCaseInsensitive() {
				final String inputProduct = "farine";
				final Integer expectedProductIndex = 3;
				
				Ingredient g = parser.build(0.0, "", inputProduct);
				
				assertThat(g.getProduct()).isEqualTo(prds.get(expectedProductIndex));
			}
			
			@Test
			public void shouldFindProductWithAccents() {
				final String inputProduct = "Crême";
				final Integer expectedProductIndex = 2;
				
				Ingredient g = parser.build(0.0, "", inputProduct);
				
				assertThat(g.getProduct()).isEqualTo(prds.get(expectedProductIndex));
			}
			
			@Test
			public void shouldFindMultiWordProduct() {
				final String inputProduct = "Sucre glace";
				final Integer expectedProductIndex = 4;
				
				Ingredient g = parser.build(0.0, "", inputProduct);
				
				assertThat(g.getProduct()).isEqualTo(prds.get(expectedProductIndex));
			}
			
			@Test
			public void shouldNotFindSuperStringProduct() {
				final String inputProduct = "Sucre";
				final Integer expectedProductIndex = 5;
				
				Ingredient g = parser.build(0.0, "", inputProduct);
				
				assertThat(g.getProduct()).isEqualTo(prds.get(expectedProductIndex));
			}
		}

	}
	
	
}
