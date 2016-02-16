module.exports = function(AuthService, AUTH_EVENTS, $rootScope) {
  var vm = this;

  // Attributes and initialization

  // Public methods
  vm.doLogin = doLogin;
  vm.doLogout = doLogout;
  vm.isAuthenticated = AuthService.isAuthenticated;

  // Implementations
  function doLogin() {
    AuthService.doLogin(vm.login, vm.password);
  }

  function doLogout() {
    AuthService.doLogout();
  }
}
