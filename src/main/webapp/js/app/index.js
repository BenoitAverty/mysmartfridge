'use strict';

var angular = require('angular');
require('angular-route');

// Déclaration du module
var mysmartfridge = angular.module('mySmartFridge', ['ngRoute']);

mysmartfridge.config(function($routeProvider) {

  $routeProvider.when('/random-recipe', {
    templateUrl: 'partials/random_recipe.html',
    controller: 'RandomRecipeCtrl',
  })
  .when('/about', {
    templateUrl: 'partials/about.html'
  })
  .when('/login', {
    templateUrl: 'partials/login_page.html'
  })
  .otherwise({
    redirectTo: '/random-recipe',
  });
});

require('./controllers')
require('./services')
