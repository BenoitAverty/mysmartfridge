module.exports = function($http) {
  var serviceInstance = {
      getRandomRecipe: getRandomRecipe,
      addRecipe: addRecipe
  };
  return serviceInstance;

  function getRandomRecipe() {
    return $http({
      method: 'GET',
      url: '/api/recipes/random'
    }).then(
      function(response) {
        return response.data;
      },
      function(response) {
        return response.status;
      }
    );
  }

  /**
   * Calls the api endpoint to add a recipe and returns a promise.
   */
  function addRecipe(recipe) {
    return $http({
      method: 'POST',
      url: '/api/recipes',
      data: recipe
    });
  }
}
