'use strict';

var angular = require('angular');
require('angular-route');

// Déclaration du module
var mysmartfridge = angular.module('mySmartFridge', ['ngRoute']);

require('./config')
require('./controllers')
require('./services')
