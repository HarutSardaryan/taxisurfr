(function () {
    'use strict';

    angular
        .module('formApp')
        .directive('focus',
            function ($timeout) {
                return {
                    scope: {
                        trigger: '@focus'
                    },
                    link: function (scope, element) {
                        scope.$watch('trigger', function (value) {
                            if (value === 'true') {
                                $timeout(function () {
                                    element[0].focus();
                                });
                            }
                        });
                    }
                };
            }
        )
        .directive('focusOn', function ($timeout) {
            return function (scope, elem, attr) {
                scope.$on(attr.focusOn, function (e) {
                    elem[0].focus();
                });
            };
        });

})();