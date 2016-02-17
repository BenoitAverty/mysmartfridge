module.exports = function ($q, $rootScope, AuthService) {
  return {
    resolve: function () {
      return AuthService.getAuthInfo();
    }
  };
};
