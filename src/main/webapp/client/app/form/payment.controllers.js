(function () {
    'use strict';

    angular
        .module('formApp')
        .controller('PaymentController', PaymentController);

    PaymentController.$inject = ['$scope'];

    function PaymentController($scope) {
        $scope.handleStripe = function (status, response) {
            if (response.error) {
                // there was an error. Fix it.
                console.log('error:' + response);
            } else {
                // got stripe token, now charge it or smt
                token = response.id;
                console.log('success:' + token);
            }
        };
    }

})();