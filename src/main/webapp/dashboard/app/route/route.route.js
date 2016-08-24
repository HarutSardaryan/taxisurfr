(function () {
    'use strict';

    angular
        .module('app.route')
        .run(appRun);

    appRun.$inject = ['routerHelper'];
    /* @ngInject */
    function appRun(routerHelper) {
        routerHelper.configureStates(getStates());
    }

    function getStates() {
        return [
            {
                state: 'route',
                config: {
                    url: '/',
                    templateUrl: 'app/route/route.html',
                    controller: 'RouteController',
                    controllerAs: 'vm',
                    title: 'dashboard',
                    settings: {
                        nav: 1,
                        content: '<i class="fa fa-dashboard"></i> Route'
                    }
                }
            }
        ];
    }
})();
