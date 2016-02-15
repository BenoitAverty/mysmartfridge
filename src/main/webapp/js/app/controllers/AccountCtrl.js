module.exports = function(AccountService) {
  var vm = this;

  // Attributes and initialization
  vm.working = false;
  vm.loggedIn = AccountService.isLoggedIn();

  // Public methods
  vm.doLogin = doLogin;
  vm.doLogout = doLogout;

  // Implementations
  function doLogin() {
    vm.working = true;
    AccountService.doLogin(vm.login, vm.password, handleLoginSuccess, handleLoginFailure);
  }

  function doLogout() {
    AccountService.doLogout(function() {
      vm.loggedIn = false;
    })
  }

  function handleLoginSuccess() {
    vm.loggedIn = true;
    vm.working = false;
  }

  function handleLoginFailure() {
    vm.working = false;
    vm.loggedIn = false;
  }
}
