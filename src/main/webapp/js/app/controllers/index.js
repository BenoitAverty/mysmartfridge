var app = require('angular').module('mySmartFridge');

app.controller('RandomRecipeCtrl', ['RecipeService', 'IngredientFormatter', require('./RandomRecipeCtrl')]);
app.controller('AddRecipeCtrl', ['RecipeService', require('./AddRecipeCtrl')]);
app.controller('AccountCtrl', ['AuthService', require('./AccountCtrl')]);
