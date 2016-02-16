var app = require('angular').module('mySmartFridge');

app.factory('RecipeService', ['$http', require('./RecipeService')]);
app.factory('AuthService', ['$http', '$httpParamSerializer', '$q', 'USER_ROLES', require('./AuthService')]);
