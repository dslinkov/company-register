'use strict';

tinyApp.controller('DashboardController', ['$scope', 'dashboardService',

  function DashboardController($scope, dashboardService) {

    $scope.companies = [];

    $scope.getCompanies = function() {

      dashboardService.getCompanies().then(success);

      function success(response) {

        $scope.companies = response.data.companies;

      }

    };

    $scope.getCompanies();



  }
]);


tinyApp.factory('dashboardService', ['$http',

  function($http) {

    return {

      getCompanies: function() {

        var promise = $http({
          method: 'GET',
          url: 'api/company'
        });

        return promise;

      }

    };

  }
]);