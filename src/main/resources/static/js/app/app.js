'use strict';


var tinyApp = angular.module('tinyApp', ['ngSanitize', 'ngRoute']);

tinyApp.config(function($locationProvider, $provide) {

	// use the HTML5 History API?  true or false
	$locationProvider.html5Mode({
	    enabled: true,
	    requireBase: false
	});


    $provide.decorator('$q', ['$delegate', '$rootScope', function($delegate, $rootScope) {
        var pendingPromisses = 0;
        $rootScope.$watch(
          function() { return pendingPromisses > 0; },
          function(loading) { $rootScope.loading = loading; }
        );
        var $q = $delegate;
        var origDefer = $q.defer;
        $q.defer = function() {
          var defer = origDefer();
          pendingPromisses++;

          defer.promise["finally"] (function() {
            pendingPromisses--;
          });

          return defer;
        };
        return $q;
      }]);
});


tinyApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/registry/company', {
		templateUrl : 'templates/dashboard.html',
		controller : 'DashboardController'
	}).when('/registry/company/:companyId', {
		templateUrl : '/registry/templates/employee.html',
		controller : 'EmployeeController'
	}).when('/registry/home', {
		templateUrl : 'templates/dashboard.html',
		controller : 'DashboardController'
	}).when('/registry/add-company', {
		templateUrl : 'templates/company.html',
		controller : 'CompanyController'
	})
} ]);
