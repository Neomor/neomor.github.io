(function () {
  'use strict';

  angular.module('auction', ['ngRoute'])
      .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
              templateUrl: 'views/home.html',
              controller: 'HomeController as ctrl'
            })
            .when('/search', {
              templateUrl: 'views/search.html',
              controller: 'SearchController as ctrl'
            })
            .otherwise({
              redirectTo: '/'
            });
  }]);


}());
