module.exports = function($rootScope, $state, AuthService) {
  $rootScope.$on('$stateChangeStart', function (event, next) {

    if(next.data) {
        var authorizedRoles = next.data.authorizedRoles;
    }
    
    if (!AuthService.isAuthorized(authorizedRoles)) {
      event.preventDefault();
      if (AuthService.isAuthenticated()) {
        // user is not allowed
        $state.go('random-recipe');
      } else {
        // user is not logged in
        $state.go('login-required');
      }
    }
  });
}
