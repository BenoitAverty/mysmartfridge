module.exports = function($routeProvider) {

  $routeProvider.when('/random-recipe', {
    templateUrl: 'partials/random_recipe.html',
  })
  .when('/about', {
    templateUrl: 'partials/about.html'
  })
  .when('/add-recipe', {
    templateUrl: 'partials/add_recipe.html'
  })
  .otherwise({
    redirectTo: '/random-recipe',
  });
}
