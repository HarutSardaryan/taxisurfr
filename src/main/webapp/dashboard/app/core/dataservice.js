(function () {
    'use strict';

    angular
        .module('app.core')
        .factory('dataservice', dataservice);

    dataservice.$inject = ['$http', '$q', 'exception', 'logger'];
    /* @ngInject */
    function dataservice($http, $q, exception, logger) {
        var service = {
            getRoutes: getRoutes,
            getMessageCount: getMessageCount
        };

        return service;

        function getMessageCount() {
            return $q.when(72);
        }

        function getRoutes() {
            return $http.get('ws/api/routes')
                .then(success)
                .catch(fail);

            function success(response) {
                return response.data;
            }

            function fail(e) {
                return exception.catcher('XHR Failed for getRoutes')(e);
            }
        }
    }
})();
