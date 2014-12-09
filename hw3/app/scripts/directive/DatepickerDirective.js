(function () {
    'use strict';

    var datepickerDirective = function() {
        return {
            restrict: 'A',
            link: function (scope, el) {
                el.datepicker({
                    autoclose: true
                });
                el.datepicker('setDate', new Date());
            }
        }
    }

    angular.module('auction').directive('auctionDatepicker', datepickerDirective);
}());