module.exports = function(AuthService) {
  var vm = this;

  // Attributes and initialization

  // Public methods
  vm.doLogin = doLogin;
  vm.doLogout = doLogout;

  // Implementations
  function doLogin() {
    AuthService.doLogin(vm.login, vm.password);
  }

  function doLogout() {
    AuthService.doLogout();
  }
}
