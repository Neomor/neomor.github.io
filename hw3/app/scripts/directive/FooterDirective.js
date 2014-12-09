(function () {
  'use strict';

  var footerDirective = function () {
    return {
      scope: false,
      restrict: 'E',
      templateUrl: 'views/partial/FooterDirective.html'
    };
  };

  angular.module('auction').directive('auctionFooter', footerDirective);
}());
