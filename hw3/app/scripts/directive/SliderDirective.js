(function () {
    'use strict';

    var sliderDirective = function() {
        return {
            restrict: 'A',
            link: function (scope, el) {
                el.slider({
                    min: 10,
                    max: 10000,
                    step: 10,
                    value: [1000, 9000],
                    tooltip: "hide"
                }).on("slide", function(e){
                    angular.element("#startPrice").val(e.value[0]);
                    angular.element("#endPrice").val(e.value[1]);
                });
            }
        }
    }


    angular.module('auction').directive('auctionSlider', sliderDirective);

}());