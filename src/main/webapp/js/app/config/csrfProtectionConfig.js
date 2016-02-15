/**
 * Put a random token in the CSRF-TOKEN cookie before each request. The X-CSRF-TOKEN header of the request must match this token.
 */
module.exports = function($httpProvider) {
  //get a random token (https://gist.github.com/jed/982883)
  function b(a){return a?(a^Math.random()*16>>a/4).toString(16):([1e16]+1e16).replace(/[01]/g,b)};

  $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
  $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';

  $httpProvider.interceptors.push(function() {
    return {
      'request': function(response) {
        document.cookie = 'CSRF-TOKEN='+b();
        return response;
      }
    }
  })
}
