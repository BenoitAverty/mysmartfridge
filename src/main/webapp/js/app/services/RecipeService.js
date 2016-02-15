module.exports = function($http) {
  var serviceInstance = {
      getRandomRecipe: getRandomRecipe,
      addRecipe: addRecipe
  };
  return serviceInstance;

  function getRandomRecipe(callback, error) {
    $http({
      method: 'GET',
      url: '/api/recipes/random'
    }).then(
      function(response) {
        callback(response.data);
      },
      function(response) {
        error(response.status);
      }
    );
  }

  function addRecipe(recipe, callback, error) {
    $http({
      method: 'POST',
      url: '/api/recipes',
      data: recipe
    }).then(
      function(response) {
        callback();
      },
      function(response) {
        error();
      }
    );
  }
}
