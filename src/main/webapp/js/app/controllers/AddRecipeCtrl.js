module.exports = function(RecipeService) {
  var vm = this;

  // Attributes and initialization
  vm.recipe = {
    title: '',
    nbPeople: 0,
    prepTime: 0,
    cookTime: 0
  }

  vm.adding=true;

  // Public methods
  vm.addRecipe = addRecipe;

  // Implementations
  function addRecipe() {
    vm.adding = true;
    RecipeService.addRecipe(vm.recipe, handleSuccess, handleFailure);
  }

  function handleSuccess() {
    vm.working = false;
  }

  function handleFailure() {
    vm.working = false;
  }
}
