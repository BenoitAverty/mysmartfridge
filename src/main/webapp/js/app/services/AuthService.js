module.exports = function($http, $httpParamSerializer, $q, USER_ROLES) {

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
    .then(
      function(response) {
        isAuthenticated = true;
        email = login;
      }
    )
  }

  function getAuthInfo() {
    $http({
      method: 'GET',
      url: '/api/users/current'
    })
    .then(
      function(response) {
        isAuthenticated = true;
        email = response.data.email;
      },
      function(response) {
        isAuthenticated = false;
        email = '';
      }
    )
  }

  function doLogout() {
    return $http({
      method: 'POST',
      url: '/api/logout'
    })
    .then(
      function() {
        isAuthenticated = false;
        email = '';
      }
    )
  }

  function isAuthorized(authorizedRoles) {
    if (!angular.isArray(authorizedRoles)) {
      authorizedRoles = [authorizedRoles];
    }
    return (isAuthenticated && authorizedRoles.indexOf(role) !== -1);
  }
}
