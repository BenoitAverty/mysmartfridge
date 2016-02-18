module.exports = function(RecipeService) {
  var vm = this;

  // Attributes and initialization
  vm.recipe = {
    title: 'Test',
    nbPeople: 4,
    prepTime: 20,
    cookTime: 15,
    ingredients: ['500g de sucre', '1kg de farine'],
    steps: ['Étape 1', 'Étape 2']
  }

  vm.working=false;

  // Public methods
  vm.addRecipe = addRecipe;
  vm.addIngredient = addIngredient;
  vm.removeIngredient = removeIngredient;
  vm.addStep = addStep;
  vm.removeStep = removeStep;

  // Implementations
  function addIngredient() {
    vm.recipe.ingredients.push('');
  }

  function removeIngredient(index) {
    vm.recipe.ingredients.splice(index, 1);
  }

  function addStep() {
    vm.recipe.steps.push('');
  }

  function removeStep(index) {
    vm.recipe.steps.splice(index, 1);
  }

  function addRecipe() {
    vm.working = true;
    RecipeService.addRecipe(vm.recipe).then(handleSuccess, handleFailure);
  }

  function handleSuccess() {
    vm.working = false;
  }

  function handleFailure() {
    vm.working = false;
  }
}
