// create our angular app and inject ngAnimate and ui-router
// =============================================================================

angular.module('formApp', ['ngAnimate', 'ui.router', 'ui.bootstrap', 'angular-google-gapi',
    'angular-loading-bar', 'angular-stripe'])

// configuring our routes
// =============================================================================
    .config(function ($stateProvider, $urlRouterProvider, stripeProvider) {

        $stateProvider

        // route to show our basic form (/form)
            .state('form', {
                url: '/form',
                templateUrl: 'app/form/form.html',
                controller: 'FormController'
            })

            // nested states
            // each of these sections will have their own view
            // url will be nested (/form/profile)
            .state('form.transport', {
                url: '/transport',
                templateUrl: 'app/form/form-transport.html'

            })

            .state('form.route', {
                url: '/route?route',
                templateUrl: 'app/form/form.html',
                controller: 'FormController'
                //
                //controller: function ($scope, $stateParams) {
                //    $scope.routeId = $stateParams.route;
                //}

            })

            // url will be /form/interests
            .state('form.details', {
                url: '/details',
                templateUrl: 'app/form/form-details.html'
            })

            // url will be /form/interests
            .state('form.summary', {
                url: '/summary',
                templateUrl: 'app/form/form-summary.html'
            })

            // url will be /form/payment
            .state('form.payment', {
                url: '/payment',
                templateUrl: 'app/form/form-payment.html',
                controller: 'PaymentController'
            })

            // url will be /form/confirmation
            .state('form.confirmation', {
                url: '/confirmation',
                templateUrl: 'app/form/form-confirmation.html'
            })

        ;

        // catch all route
        // send users to the form page
        //$urlRouterProvider.otherwise('/form/transport');

        $urlRouterProvider.otherwise(function ($injector, $location) {
            var searchObject = $location.search().route;
            return '/form/transport';
        });


    })


    // .config(function (stripeProvider) {
    //     stripeProvider.setPublishableKey("pk_test_rcKuNpP9OpTri7twmZ77UOI5");

    // $http.post('http://localhost:8080/taxisurfr-1.0/rest/api/session.get', session)
    //     .then(function (response) {
    //         stripeProvider.setPublishableKey(response.data.stripePublishable);
    //     });
    // })

    .run(['GAuth', '$http', 'GData', '$state', '$rootScope', '$window','$location',
        function (GAuth, $http, GData, $state, $rootScope, $window, $location) {

            // $rootScope.gdata = GData;
            //
            // var CLIENT = '526374069175-4vv42arm0ksdr9a1lgkve6vbktfkmlvv.apps.googleusercontent.com';
            //
            // $http.load('taxisurfr', 'v1', getBase($window) + '_ah/api');
            // GApi.load('calendar', 'v3');
            // GAuth.setClient(CLIENT);
            // GAuth.setScope('https://www.googleapis.com/auth/userinfo.email ' +
            //     'https://www.googleapis.com/auth/calendar.readonly');

            var session = {url:$location.absUrl()};
            console.log('url='+session.url);
            $http.post(getBase($window) + 'rest/api/session.get', session)
                .then(function (response) {
                    Stripe.setPublishableKey(response.data.stripePublishable);
                });
        }
    ]);


    //.factory("traceService", function (error) {
    //    return ({print: StackTrace.fromError(error).then(callback).catch(errback);});
    //})

    //.provider("$exceptionHandler", {
    //    $get: function (exceptionLoggingService) {
    //        return (exceptionLoggingService);
    //    }
    //})

    //.factory('$exceptionHandler', ['$window',
    //    function ($window) {
    //        return function (exception, cause) {
    //
    //            var callback = function (stackframes) {
    //                var stringifiedStack = stackframes.map(function (sf) {
    //                    return sf.toString();
    //                }).join('\n');
    //                console.log(stringifiedStack);
    //                sendError(exception.toString(), stringifiedStack, cause, $window.location.href)
    //            };
    //            var errback = function (err) {
    //                console.log(err.message);
    //            };
    //            StackTrace.get().then(callback, errback)
    //
    //        };
    //    }
    //])


function sendError(errorMessage, stackTrace, cause, href) {
    try {
        // use our traceService to generate a stack trace var stackTrace = traceService.print({e: exception});
        // use AJAX (in this example jQuery) and NOT // an angular service such as $http
        jQuery.ajax({
            type: 'POST',
            url: '/taxisurfr/logging',
            contentType: 'application/json',
            data: angular.toJson({
                url: href,
                message: errorMessage,
                type: 'exception',
                stackTrace: stackTrace,
                cause: ( cause || '')
            })
        });
    } catch (loggingError) {
        console.log('Error server-side logging failed');
        console.log(loggingError);
    }
}

var getBase = function (window) {
    console.log(window.location.hostname);
    console.log(window.location.port);
    if (window.location.hostname === 'localhost') {
        //return '//localhost:3000/';
        return 'http://localhost:8080/taxisurfr-1.0/';
        //return 'https://taxigangsurf.appspot.com/';
    } else {

        return 'https://taxisurfr-taxisurfr.rhcloud.com/';
        //return 'https://gobygang.appspot.com/';
    }
};

