(function () {
    'use strict';

    var auctionProductPropertiesDirective = function() {
        return {
            scope: true,
            restrict: 'E',
            templateUrl: 'views/partial/ProductPropertiesDirective.html'
        };
    }

    angular.module('auction').directive('auctionProductProperties', auctionProductPropertiesDirective);

}());