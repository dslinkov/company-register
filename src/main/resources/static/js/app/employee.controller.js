'use strict';

tinyApp.controller('EmployeeController', ['$scope', '$routeParams', 'employeeService',

  function EmployeeController($scope, $routeParams, employeeService) {

    $scope.companyEmployees = [];

    $scope.getCompanyEmployees = function() {

    	employeeService.getCompanyEmployees($routeParams.companyId).then(success);

      function success(response) {

        $scope.companyEmployees = response.data;

      }

    };

    $scope.getCompanyEmployees();



  }
]);


tinyApp.factory('employeeService', ['$http',

  function($http) {

    return {

    	getCompanyEmployees: function(companyId) {

        var promise = $http({
          method: 'GET',
          url: '/registry/api/company/' + companyId + '/employee'
        });

        return promise;

      }

    };

  }
]);