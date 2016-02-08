var app = require('angular').module('mySmartFridge');

app.factory('RecipeService', ['$http', require('./RecipeService')]);
