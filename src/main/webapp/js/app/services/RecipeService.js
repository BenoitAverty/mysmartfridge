module.exports = function($http) {
  var serviceInstance = {
      getRandomRecipe: getRandomRecipe
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
}
