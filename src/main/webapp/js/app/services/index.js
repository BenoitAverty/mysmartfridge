var app = require('angular').module('mySmartFridge');

app.factory('IngredientFormatter', require('./IngredientFormatter'));
app.factory('RecipeService', ['$http', require('./RecipeService')]);
app.factory('AuthService', ['$http', '$httpParamSerializer', 'USER_ROLES', require('./AuthService')]);
app.factory('AuthResolver', ['$q', '$rootScope', 'AuthService', require('./AuthResolver')]);
