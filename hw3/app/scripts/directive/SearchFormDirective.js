(function () {
    'use strict';

    var auctionSearchFormDirective = function() {
        return {
            scope: true,
            restrict: 'E',
            templateUrl: 'views/partial/SearchFormDirective.html'
        };
    }

    angular.module('auction').directive('auctionSearchForm', auctionSearchFormDirective);
}());