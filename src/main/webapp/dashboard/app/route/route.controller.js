(function () {
    'use strict';

    angular
        .module('app.route')
        .controller('RouteController', RouteController);

    RouteController.$inject = ['$q', 'dataservice', 'logger'];
    /* @ngInject */
    function RouteController($q, dataservice, logger) {
        var vm = this;
        vm.news = {
            title: 'helloWorld',
            description: 'Hot Towel Angular is a SPA template for Angular developers.'
        };
        vm.messageCount = 0;
        vm.routes = [];
        vm.title = 'Dashboard';

        activate();

        function activate() {
            var promises = [getMessageCount(), getRoutes()];
            return $q.all(promises).then(function () {
                logger.info('Activated Dashboard View');
            });
        }

        function getMessageCount() {
            return dataservice.getMessageCount().then(function (data) {
                vm.messageCount = data;
                return vm.messageCount;
            });
        }

        function getRoutes() {
            return dataservice.getRoutes().then(function (data) {
                vm.routes = data;
                return vm.routes;
            });
        }
    }
})();
