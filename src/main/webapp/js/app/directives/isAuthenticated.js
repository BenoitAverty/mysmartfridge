module.exports = function(AuthService) {
  return {
    restrict: 'A',
    link: function link(scope, element, args) {
      var setVisible = function () {
        element.removeClass('hidden');
      },
      setHidden = function () {
        element.addClass('hidden');
      },
      defineVisibility = function (reset) {

        if (reset) {
          setVisible();
        }

        if(AuthService.isAuthenticated()) {
          setVisible();
        }
        else {
          setHidden();
        }
      };

      defineVisibility(true);

      scope.$watch(function(scope) {
        return AuthService.isAuthenticated();
      }, function(newValue) {
        defineVisibility(true);
      });
    }
  }
}
