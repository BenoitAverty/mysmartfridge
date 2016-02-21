module.exports = function() {
  var serviceInstance = {
      format: format
  };

  init();

  return serviceInstance;

  var units;

  function init() {
    units = {
      "GRAM": ["gramme de", "grammes de"],
  		"KILOGRAM": ["kilo de", "kilos de"],
  		"LITER": ["litre de", "litres de"],
  		"MILLILITER": ["millilitre de", "millilitres de"],
  		"NO_UNIT": ["",""],
  		"TABLESPOON": ["cuillère à café de", "cuillères à café de"],
  		"CUP": ["verre de", "verres de"]
    }
  }

  /**
   * Format an ingredient into a string
   */
  function format(ingredient) {
    var quantity = ingredient.quantity;
    var unit = ingredient.unit;
    var product = ingredient.product;

    return quantity + ' ' + ((quantity != 1) ? units[unit][1] : units[unit][0]) + " " + product;
  }
}
