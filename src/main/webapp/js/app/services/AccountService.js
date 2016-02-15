module.exports = function($http, $httpParamSerializer) {
  var serviceInstance = {
      doLogin: doLogin,
      isLoggedIn: isLoggedIn
  };
  return serviceInstance;

  function doLogin(login, password, callback, error) {
    $http({
      method: 'POST',
      url: '/api/login',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      data: $httpParamSerializer({"login": login, "password": password})
    })
    .then(
      callback, error
    )
  }

  function isLoggedIn() {
    $http({
      method: 'GET',
      url: '/api/users/current'
    });

    return false;
  }
}
