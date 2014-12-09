(function () {
    'use strict';

    var auctionProductGalleryDirective = function() {
        return {
            scope: true,
            restrict: 'E',
            templateUrl: 'views/partial/ProductGalleryDirective.html'
        };
    }

    angular.module('auction').directive('auctionProductGallery', auctionProductGalleryDirective);
}());