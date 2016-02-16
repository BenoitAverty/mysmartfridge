'use strict';

var angular = require('angular');
var angularUiRouter = require('angular-ui-router');

// Déclaration du module
var mysmartfridge = angular.module('mySmartFridge', [angularUiRouter]);

require('./config');
require('./services');
require('./controllers');
require('./directives');
