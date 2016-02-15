var mysmartfridge = require('angular').module('mySmartFridge');

mysmartfridge.config(['$routeProvider', require('./routeConfig')]);
mysmartfridge.config(['$httpProvider', require('./csrfProtectionConfig')])
