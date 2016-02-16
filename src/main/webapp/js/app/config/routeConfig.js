module.exports = function($stateProvider, $urlRouterProvider, USER_ROLES) {

  $urlRouterProvider.otherwise("/random-recipe");

  $stateProvider
    .state('random-recipe', {
      url: "/random-recipe",
      templateUrl: "partials/random_recipe.html"
    })
    .state('about', {
      url: "/about",
      templateUrl: "partials/about.html"
    })
    .state('add-recipe', {
      url: "/add-recipe",
      templateUrl: "partials/add_recipe.html",
      data: {
        authorizedRoles: [USER_ROLES.user]
      }
    });
}
