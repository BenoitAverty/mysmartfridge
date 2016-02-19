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

import com.mysmartfridge.domain.products.Product;
import com.mysmartfridge.domain.products.ProductsRepository;
import com.mysmartfridge.domain.recipes.Quantity.Unit;
import com.nitorcreations.junit.runners.NestedRunner;

/**
 * Test for the domain service {@link IngredientParser}.
 */
@RunWith(NestedRunner.class)
public class IngredientParserTest {
	
	/**
	 * System under test.
	 * 
	 * @see IngredientParser
	 */
	@InjectMocks
	IngredientParser parser;
	
	/**
	 * Mock products repository.
	 * 
	 * @see ProductsRepository
	 */
	@Mock
	ProductsRepository productsRepo;
	
	@Before
	public void beforeEach() {
		parser = new IngredientParser();
		MockitoAnnotations.initMocks(this);
	}
	
	public class parseMethod {
		
		@SuppressWarnings("serial")
		List<Product> prds = new ArrayList<Product>() {{
			add(new Product("Lait"));
			add(new Product("Oeufs"));
			add(new Product("Crême"));
			add(new Product("Farine"));
			add(new Product("Sucre"));
		}};
		
		@Before
		public void beforeEach() {
			when(productsRepo.findAll()).thenReturn(prds);
		}
		
		public class withASimpleInput {
			
			/** value of the quantity expected in output **/
			private Double outQuantityValue = 220.0;
			/** index in "prds" of the product expected in output */
			private Integer outProductIndex = 3;
			/** unit expected in output */
			private Unit outUnit = Unit.GRAM;
			/** input string */
			private String inputString = "220g de farine";
					
			@Test
			public void shouldReturnIngredientWithCorrectProduct() {
				Ingredient g = parser.parse(inputString);
				
				assertThat(g.getProduct().getName()).isEqualTo(prds.get(outProductIndex).getName());
			}
			
			@Test
			public void shouldReturnQuantityWithCorrectValue() {
				Ingredient g = parser.parse(inputString);
				
				assertThat(g.getQuantity().getValue()).isEqualTo(outQuantityValue);
			}
			
			@Test
			public void shouldReturnQuantityWithCorrectUnit() {
				Ingredient g = parser.parse(inputString);
				
				assertThat(g.getQuantity().getUnit()).isEqualTo(outUnit);
			}
		}
		
		public class withAMultiWordUnit {
			/** value of the quantity expected in output **/
			private Double outQuantityValue = 3.0;
			/** index in "prds" of the product expected in output */
			private Integer outProductIndex = 3;
			/** unit expected in output */
			private Unit outUnit = Unit.TABLESPOON;
			/** input string */
			private String inputString = "3 cuillères à café de farine";
			
			@Test
			public void shouldReturnIngredientWithCorrectProduct() {
				Ingredient g = parser.parse(inputString);
				
				assertThat(g.getProduct().getName()).isEqualTo(prds.get(outProductIndex).getName());
			}
			
			@Test
			public void shouldReturnQuantityWithCorrectValue() {
				Ingredient g = parser.parse(inputString);
				
				assertThat(g.getQuantity().getValue()).isEqualTo(outQuantityValue);
			}
			
			@Test
			public void shouldReturnQuantityWithCorrectUnit() {
				Ingredient g = parser.parse(inputString);
				
				assertThat(g.getQuantity().getUnit()).isEqualTo(outUnit);
			}
		}
		
	}
	
	
}
