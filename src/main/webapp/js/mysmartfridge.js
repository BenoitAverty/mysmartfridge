
// Put jQuery in the global namespace (for bootstrap js libs)
global.jQuery = require('jquery');
global.$ = global.jQuery;

// Load bootstrap js files
require('bootstrap');

// load the app
require('./app')
