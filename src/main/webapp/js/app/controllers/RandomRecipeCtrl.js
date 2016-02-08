module.exports = function(RecipeService) {
  var vm = this;

  // Attributes and initialization
  vm.title = '';
  vm.ingredients = '';
  vm.steps = '';
  randomize();
  
  // Public methods
  vm.randomize = randomize();

  // Implementations
  function randomize() {
    RecipeService.getRandomRecipe(
      function(recipe) {
        vm.title = recipe.title;
        vm.ingredients = recipe.ingredients;
        vm.steps = recipe.steps;
      }
    )
  }
}
