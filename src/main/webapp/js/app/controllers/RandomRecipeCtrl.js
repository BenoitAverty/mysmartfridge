module.exports = function(RecipeService) {
  var vm = this;

  // Attributes and initialization
  vm.recipe = {
    tid: 0,
    title: '',
    nbPeople: 0,
    prepTime: 0,
    cookTime: 0,
    ingredients: [],
    steps: []
  }
  randomize();

  // Public methods
  vm.randomize = randomize;

  // Implementations
  function randomize() {
    RecipeService.getRandomRecipe(
      function(recipe) {
        angular.extend(vm.recipe, recipe)
      },
      function(status) {
        // Show error ?
      }
    )
  }
}
