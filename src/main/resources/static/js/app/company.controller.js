'use strict';

tinyApp.controller('CompanyController', [
		'$scope',
		'$routeParams',
		'$location',
		'companyService',

		function EmployeeController($scope, $routeParams, $location,
				companyService) {

			$scope.company = {
				name : "",
				address : "",
				city : "",
				country : "",
				phone : "",
				email : ""
			};

			$scope.addNewCompany = function() {

				if ($scope.companyForm.$valid) {
					companyService.getCompanyEmployees($scope.company).then(
							success);
				}

				function success(response) {

					$location.path("/registry/home").replace();

				}

			};

		} ]);

tinyApp.factory('companyService', [ '$http',

function($http) {

	return {

		getCompanyEmployees : function(company) {

			var promise = $http({
				method : 'POST',
				url : '/registry/api/company/',
				data : company
			});

			return promise;

		}

	};

} ]);