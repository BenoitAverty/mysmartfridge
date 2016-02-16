var app = require('angular').module('mySmartFridge');

app.directive('isAuthenticated', ['AuthService', require('./isAuthenticated')]);
//app.directive('hasAnyRole', ['AuthService', require('./hasAnyRole')]);
//app.directive('hasRole', ['AuthService', require('./hasRole')]);
