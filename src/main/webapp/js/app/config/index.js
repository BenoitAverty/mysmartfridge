var mysmartfridge = require('angular').module('mySmartFridge');

require('./constants');

mysmartfridge.config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES', require('./routeConfig')]);
mysmartfridge.config(['$httpProvider', require('./csrfProtectionConfig')]);
mysmartfridge.run(['AuthService', require('./authStatusInit')]);
mysmartfridge.run(['$rootScope', '$state', 'AuthService', require('./routeAuthAccess')])
