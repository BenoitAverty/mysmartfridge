module.exports = function($http, $httpParamSerializer, USER_ROLES) {

  // interface
  var serviceInstance = {
      doLogin: doLogin,
      doLogout: doLogout,
      isAuthenticated: function() { return isAuthenticated },
      getUserEmail: function() { return email },
      isAuthorized: isAuthorized,
      getAuthInfo: getAuthInfo
  };

  //Private part
  var email = '';
  var isAuthenticated = false;
  var role = '';

  return serviceInstance;

  function doLogin(login, password) {
    return $http({
      method: 'POST',
      url: '/api/login',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      data: $httpParamSerializer({"login": login, "password": password})
    })
    .then(getAuthInfo);
  }

  function getAuthInfo() {
    return $http({
      method: 'GET',
      url: '/api/users/current'
    })
    .then(
      function(response) {
        isAuthenticated = true;
        email = response.data.email;
        role = response.data.role;
      },
      purgeAuthenticationInfo
    );
  }

  // Log the user out of the api and the application.
  function doLogout() {
    return $http({
      method: 'POST',
      url: '/api/logout'
    })
    .then(purgeAuthenticationInfo);
  }

  // Purge the user informations, on logout or login failure.
  function purgeAuthenticationInfo() {
    isAuthenticated = false;
    email = '';
    role = USER_ROLES.anonymous;
  }

  function isAuthorized(authorizedRoles) {
    if(!angular.isDefined(authorizedRoles)) {
      return true;
    }

    if (!angular.isArray(authorizedRoles)) {
      authorizedRoles = [authorizedRoles];
    }

    return (isAuthenticated && authorizedRoles.indexOf(role) !== -1);
  }
}
