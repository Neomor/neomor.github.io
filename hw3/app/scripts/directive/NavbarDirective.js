(function () {
  'use strict';

  var navbarDirective = function () {
    return {
      scope: false,
      restrict: 'E',
      templateUrl: 'views/partial/NavbarDirective.html'
    };
  };

  angular.module('auction').directive('auctionNavbar', navbarDirective);
}());
