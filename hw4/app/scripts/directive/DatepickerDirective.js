(function () {
  'use strict';

  var datepickerDirectiveFactory = function () {
    return {
      scope: false,
      restrict: 'A',
      link: function (scope, element) {
        element.datepicker({
          startDate: '-0d',
          autoclose: true
        });
        element.datepicker('setDate', new Date());
      }
    };
  };

  angular.module('auction').directive('auctionDatepicker', datepickerDirectiveFactory);
}());
