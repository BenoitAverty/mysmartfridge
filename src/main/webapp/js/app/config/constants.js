var mysmartfridge = require('angular').module('mySmartFridge');

mysmartfridge.constant('USER_ROLES', {
  anonymous: "ROLE_ANONYMOUS",
  user: "ROLE_USER",
  admin: "ROLE_ADMIN"
});

mysmartfridge.constant('AUTH_EVENTS', {
  loginSuccess: 'auth-login-success',
  loginFailed: 'auth-login-failed',
  logoutSuccess: 'auth-logout-success',
  sessionTimeout: 'auth-session-timeout',
  notAuthenticated: 'auth-not-authenticated',
  notAuthorized: 'auth-not-authorized'
});
