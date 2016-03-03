module.exports = function() {
  var serviceInstance = {
      format: format
  };

  init();

  return serviceInstance;

  var units;

  function init() {
    units = {
      "GRAM": ["gramme", "grammes"],
  		"KILOGRAM": ["kilo", "kilos"],
  		"LITER": ["litre", "litres"],
  		"MILLILITER": ["millilitre", "millilitres"],
  		"NO_UNIT": ["",""],
  		"TABLESPOON": ["cuillère à café", "cuillères à café"],
  		"CUP": ["verre", "verres"]
    }
  }

  /**
   * Format an ingredient into a string
   */
  function format(ingredient) {
    var quantity = ingredient.quantity;
    var unit = ingredient.unit;
    var product = ingredient.product;

    return {
      quantity: quantity,
      product: product,
      unit: (quantity !== 1) ? units[unit][1] : units[unit][0]
    };
  }
}
