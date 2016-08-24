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
                templateUrl: 'app/form.html',
                controller: 'formController'
            })

            // nested states
            // each of these sections will have their own view
            // url will be nested (/form/profile)
            .state('form.transport', {
                url: '/transport',
                templateUrl: 'app/form-transport.html'

            })

            .state('form.route', {
                url: '/route?route',
                templateUrl: 'app/form.html',
                controller: 'formController'
                //
                //controller: function ($scope, $stateParams) {
                //    $scope.routeId = $stateParams.route;
                //}

            })

            // url will be /form/interests
            .state('form.details', {
                url: '/details',
                templateUrl: 'app/form-details.html'
            })

            // url will be /form/interests
            .state('form.summary', {
                url: '/summary',
                templateUrl: 'app/form-summary.html'
            })

            // url will be /form/payment
            .state('form.payment', {
                url: '/payment',
                templateUrl: 'app/form-payment.html'
            })

            // url will be /form/confirmation
            .state('form.confirmation', {
                url: '/confirmation',
                templateUrl: 'app/form-confirmation.html'
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
    ])

    // our controller for the form
    // =============================================================================
    .controller('formController', ['$scope', 'GApi', '$http', '$state', '$window', '$filter', 'stripe',
        function ($scope, GApi, $http, $state, $window, $filter, stripe) {

            $scope.bookingPaid = false;
            $scope.active1 = true;
            $scope.active2 = false;
            $scope.active3 = false;
            $scope.active4 = false;

            //$scope.datePicker.date = {startDate: null, endDate: null};

            $scope.dateStatus = {
                opened: false
            };

            // we will store all of our form data in this object
            //$scope.cardData = {number: '4242 4242 4242 4242', name: 'Peter Hall', cvc: '123', expMonth: 09,
            // expYear: 2019};

            $scope.booking = {
                   flightNo: 'EZ123',
                   email: 'info@xyz.com',
                   date: new Date(),
                   name: 'Peter Hall',
                   // shareWanted: true,
                   landingTime: '12:00 pm',
                   flightNo: 'QX23',
                pax: 1,
                surfboards: 0
            };
            // if ($state.params.route) {
            //     GApi.execute('taxisurfr', 'route.link', {routeId: $state.params.route}
            //     ).then(function (response) {
            //         $state.go('form.transport');
            //         $scope.route = response;
            //         $scope.asyncStart = $scope.route.start;
            //         $scope.booking.route = $scope.route.id;
            //         $scope.imgSrc = getBase($window) + 'imageservice?image=' + $scope.route.image;
            //     });
            // }
            //$scope.expMonth =  $scope.cardData.expMonth;
            //$scope.expYear = $scope.cardData.expYear;

            $scope.processing = false;
            $scope.asyncStart = null;
            $scope.selectedStart = null;
            $scope.selectedEnd = null;
            $scope.selectedroute = null;
            $scope.onSelectStart = function ($item, $model, $label) {
                $scope.selectedStart = $item.name;
            };
            $scope.onSelectEnd = function ($item, $model, $label) {
                $scope.selectedEnd = $item.name;
                $scope.noroutefound = null;
                var query = {start: $scope.selectedStart, end: $scope.selectedEnd};
                $http.post(getBase($window) + 'rest/api/route', query)
                    .then(function (response) {
                        console.log($scope.route);
                        if (response.status === 200) {
                            $scope.route = response;
                            $scope.route.pickupTypeText = $scope.route.data === 'AIRPORT' ? 'Flight No.' : 'Hotel';
                            $scope.route.pickupTimeText = $scope.route.data === 'AIRPORT' ? 'Landing Time.' : 'Pickup Time';
                        } else
                            $scope.noroutefound = 'No route found, sorry. Send an email to dispatch@taxisurfr.com and we will arrange one.';
                            $scope.active2 = false;
                            $http.post(getBase($window) + 'rest/api/missingroute', query).then(function (response) {

                            });


                        }
                        );
            };
            $scope.addBooking = function (dateText) {
                $scope.booking.dateText = $filter('date')($scope.booking.date, 'fullDate');
                $scope.booking.routeId = $scope.route.data.id;
                // var session = {url:'xxxxxxxx'};
                // $http.post(getBase($window) + 'rest/api/session.get', session)
                //     .then(function (response) {
                //       console.log("xxxxxxxxxxx")
                //     });
                //
                $http.post(getBase($window) + 'rest/api/booking', $scope.booking)
                    .then(function (response) {
                        $scope.booking = response;
                    });
            };


            $scope.charge = function () {
                $scope.processing = true;
                $scope.payment = {};
                $scope.payment.card = {
                    number: '4242424242424242',
                    cvc: '123',
                    exp_month: '08',
                    exp_year: '19'
                };
                return stripe.card.createToken($scope.payment.card)
                    .then(function (response) {
                        console.log('token created for card ending in ', response.card.last4);
                        var payment = angular.copy($scope.payment);
                        payment.card = void 0;
                        payment.token = response.id;
                        payment.bookingId = $scope.booking.data.id;
                        // payment.bookingId = 451;
                        return $http.post(getBase($window) + 'rest/api/payment',
                            payment).then(function (response) {
                            $scope.bookingPaid = response.data.ok;

                            if (response.data.ok===true){
                                    console.log('payment succeeded!')
                                $state.go('form.confirmation');
                                }else{
                                    console.log('processing error!')
                                    $scope.paymentError = response.data.error;
                                }
                        });
                    })
                    .catch(function (err) {
                        $scope.processing = true;
                        if (err.type && /^Stripe/.test(err.type)) {
                            console.log('Stripe error: ', err.message);
                        }
                        else {
                            console.log('Other error occurred, possibly with your API', err.message);
                        }
                    });
            };
            //$scope.formData = {};


            // function to process the form

            $scope.processForm = function (status, response) {
                $scope.processing = true;
                if (response.error) {
                    // there was an error. Fix it.
                    $scope.paymentError = response.error.message;
                    $scope.processing = false;
                    throw new Error($scope.paymentError);
                } else {
                    // got stripe token, now charge it or smt
                    $scope.session = {cardToken: response.id, bookingId: $scope.booking.id};
                    GApi.execute('taxisurfr', 'booking.pay', $scope.session)
                        .then(function (response) {
                            $scope.processing = false;
                            $scope.booking = response;
                            if ($scope.booking.status === 'PAID') {
                                $scope.bookingPaid = true;
                                $state.go('form.confirmation');
                            } else {
                                $scope.paymentError = $scope.booking.stripeRefusal;
                            }
                        });
                }
            };


            // $scope.onSelectEnd = function ($item, $model, $label) {
            //     $scope.route = $item;
            //     $scope.booking.route = $scope.route.id;
            //     $scope.imgSrc = getBase($window) + 'imageservice?image=' + $scope.route.image;
            //
            // };

            $scope.onSelect = function ($item, $model, $label) {
                $scope.route = $item;
                $scope.booking.route = $scope.route.id;
                $scope.imgSrc = getBase($window) + 'imageservice?image=' + $scope.route.image;
            };

            $scope.ignoreEnter = function (event) {
                event.preventDefault();
            };

            $scope.addSession = function () {
                return GApi.execute('taxisurfr', 'session.new', {
                    reference: $scope.reference,
                    route: $scope.route,
                    start: $scope.route.start,
                    end: $scope.route.end
                })
                    .then(function (response) {
                        $scope.session = response;
                        $scope.active2 = true;
                    });
            };

            $scope.resetRoute = function () {
                $scope.route = null;
            };


            $scope.addPayment = function () {
                $scope.active4 = true;
            };

            $scope.today = function () {
                $scope.dt = new Date();
            };
            $scope.today();

            $scope.clear = function () {
                $scope.dt = null;
            };

            $scope.toggleMin = function () {
                $scope.minDate = $scope.minDate ? null : new Date();
            };
            $scope.toggleMin();
            $scope.maxDate = new Date(2020, 5, 22);

            $scope.open = function ($event) {
                $scope.status.opened = true;
            };

            $scope.setDate = function (year, month, day) {
                $scope.dt = new Date(year, month, day);
            };

            $scope.dateOptions = {
                formatYear: 'yy',
                startingDay: 1
            };

            $scope.status = {
                opened: false
            };

            var tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            var afterTomorrow = new Date();
            afterTomorrow.setDate(tomorrow.getDate() + 2);
            $scope.events =
                [
                    {
                        date: tomorrow,
                        status: 'full'
                    },
                    {
                        date: afterTomorrow,
                        status: 'partially'
                    }
                ];

            $scope.getDayClass = function (date, mode) {
                $scope.booking.dateText = new Date(date).setHours(0, 0, 0, 0);
            };

            // var req = {
            //     method: 'POST',
            //     url: '/taxisurfr/session',
            //     data: { userAgent: $window.navigator.userAgent }
            // };
            //
            // $http(req).then(function successCallback(response) {
            //     $scope.reference = response.data.ip;
            //     // this callback will be called asynchronously
            //     // when the response is available
            // }, function errorCallback(response) {
            //     // called asynchronously if an error occurs
            //     // or server returns response with an error status.
            // });

            //$scope.reference = Math.random() * 10000;
            //$scope.initSession = function (reference, userAgent) {
            //    jQuery.ajax({
            //        type: "POST",
            //        url: "/taxisurfr/session",
            //        contentType: "application/json",
            //        data: angular.toJson({
            //            ref: reference,
            //            userAgent: userAgent
            //        })
            //    });
            //}
            //$scope.initSession($scope.reference, $window.navigator.userAgent);
            // $scope.statesWithFlags = [{'name':'Alabama','flag':'5/5c/Flag_of_Alabama.svg/45px-Flag_of_Alabama.svg.png'},
            //     {'name':'Alaska','flag':'e/e6/Flag_of_Alaska.svg/43px-Flag_of_Alaska.svg.png'},
            //     {'name':'Arizona','flag':'9/9d/Flag_of_Arizona.svg/45px-Flag_of_Arizona.svg.png'},
            //     {'name':'Arkansas','flag':'9/9d/Flag_of_Arkansas.svg/45px-Flag_of_Arkansas.svg.png'},
            //     {'name':'California','flag':'0/01/Flag_of_California.svg/45px-Flag_of_California.svg.png'},
            //     {'name':'Colorado','flag':'4/46/Flag_of_Colorado.svg/45px-Flag_of_Colorado.svg.png'},
            //     {'name':'Connecticut','flag':'9/96/Flag_of_Connecticut.svg/39px-Flag_of_Connecticut.svg.png'},
            //     {'name':'Delaware','flag':'c/c6/Flag_of_Delaware.svg/45px-Flag_of_Delaware.svg.png'},
            // {'name':'Florida','flag':'f/f7/Flag_of_Florida.svg/45px-Flag_of_Florida.svg.png'},
            // {'name':'Georgia','flag':'5/54/Flag_of_Georgia_%28U.S._state%29.svg/46px-Flag_of_Georgia_%28U.S._state%29.svg.png'},
            // {'name':'Hawaii','flag':'e/ef/Flag_of_Hawaii.svg/46px-Flag_of_Hawaii.svg.png'},{'name':'Idaho','flag':'a/a4/Flag_of_Idaho.svg/38px-Flag_of_Idaho.svg.png'},
            // {'name':'Illinois','flag':'0/01/Flag_of_Illinois.svg/46px-Flag_of_Illinois.svg.png'},
            // {'name':'Indiana','flag':'a/ac/Flag_of_Indiana.svg/45px-Flag_of_Indiana.svg.png'},
            // {'name':'Iowa','flag':'a/aa/Flag_of_Iowa.svg/44px-Flag_of_Iowa.svg.png'},
            // {'name':'Kansas','flag':'d/da/Flag_of_Kansas.svg/46px-Flag_of_Kansas.svg.png'},{'name':'Kentucky','flag':'8/8d/Flag_of_Kentucky.svg/46px-Flag_of_Kentucky.svg.png'},{'name':'Louisiana','flag':'e/e0/Flag_of_Louisiana.svg/46px-Flag_of_Louisiana.svg.png'},{'name':'Maine','flag':'3/35/Flag_of_Maine.svg/45px-Flag_of_Maine.svg.png'},{'name':'Maryland','flag':'a/a0/Flag_of_Maryland.svg/45px-Flag_of_Maryland.svg.png'},{'name':'Massachusetts','flag':'f/f2/Flag_of_Massachusetts.svg/46px-Flag_of_Massachusetts.svg.png'},{'name':'Michigan','flag':'b/b5/Flag_of_Michigan.svg/45px-Flag_of_Michigan.svg.png'},{'name':'Minnesota','flag':'b/b9/Flag_of_Minnesota.svg/46px-Flag_of_Minnesota.svg.png'},{'name':'Mississippi','flag':'4/42/Flag_of_Mississippi.svg/45px-Flag_of_Mississippi.svg.png'},{'name':'Missouri','flag':'5/5a/Flag_of_Missouri.svg/46px-Flag_of_Missouri.svg.png'},{'name':'Montana','flag':'c/cb/Flag_of_Montana.svg/45px-Flag_of_Montana.svg.png'},{'name':'Nebraska','flag':'4/4d/Flag_of_Nebraska.svg/46px-Flag_of_Nebraska.svg.png'},{'name':'Nevada','flag':'f/f1/Flag_of_Nevada.svg/45px-Flag_of_Nevada.svg.png'},{'name':'New Hampshire','flag':'2/28/Flag_of_New_Hampshire.svg/45px-Flag_of_New_Hampshire.svg.png'},{'name':'New Jersey','flag':'9/92/Flag_of_New_Jersey.svg/45px-Flag_of_New_Jersey.svg.png'},{'name':'New Mexico','flag':'c/c3/Flag_of_New_Mexico.svg/45px-Flag_of_New_Mexico.svg.png'},{'name':'New York','flag':'1/1a/Flag_of_New_York.svg/46px-Flag_of_New_York.svg.png'},{'name':'North Carolina','flag':'b/bb/Flag_of_North_Carolina.svg/45px-Flag_of_North_Carolina.svg.png'},{'name':'North Dakota','flag':'e/ee/Flag_of_North_Dakota.svg/38px-Flag_of_North_Dakota.svg.png'},{'name':'Ohio','flag':'4/4c/Flag_of_Ohio.svg/46px-Flag_of_Ohio.svg.png'},{'name':'Oklahoma','flag':'6/6e/Flag_of_Oklahoma.svg/45px-Flag_of_Oklahoma.svg.png'},{'name':'Oregon','flag':'b/b9/Flag_of_Oregon.svg/46px-Flag_of_Oregon.svg.png'},{'name':'Pennsylvania','flag':'f/f7/Flag_of_Pennsylvania.svg/45px-Flag_of_Pennsylvania.svg.png'},{'name':'Rhode Island','flag':'f/f3/Flag_of_Rhode_Island.svg/32px-Flag_of_Rhode_Island.svg.png'},{'name':'South Carolina','flag':'6/69/Flag_of_South_Carolina.svg/45px-Flag_of_South_Carolina.svg.png'},{'name':'South Dakota','flag':'1/1a/Flag_of_South_Dakota.svg/46px-Flag_of_South_Dakota.svg.png'},{'name':'Tennessee','flag':'9/9e/Flag_of_Tennessee.svg/46px-Flag_of_Tennessee.svg.png'},{'name':'Texas','flag':'f/f7/Flag_of_Texas.svg/45px-Flag_of_Texas.svg.png'},{'name':'Utah','flag':'f/f6/Flag_of_Utah.svg/45px-Flag_of_Utah.svg.png'},{'name':'Vermont','flag':'4/49/Flag_of_Vermont.svg/46px-Flag_of_Vermont.svg.png'},{'name':'Virginia','flag':'4/47/Flag_of_Virginia.svg/44px-Flag_of_Virginia.svg.png'},{'name':'Washington','flag':'5/54/Flag_of_Washington.svg/46px-Flag_of_Washington.svg.png'},{'name':'West Virginia','flag':'2/22/Flag_of_West_Virginia.svg/46px-Flag_of_West_Virginia.svg.png'},{'name':'Wisconsin','flag':'2/22/Flag_of_Wisconsin.svg/45px-Flag_of_Wisconsin.svg.png'},{'name':'Wyoming','flag':'b/bc/Flag_of_Wyoming.svg/43px-Flag_of_Wyoming.svg.png'}];
            $scope.points = [{
                'name': 'Colombo Airport',
                'flag': '5/5c/Flag_of_Alabama.svg/45px-Flag_of_Alabama.svg.png'
            },
                {
                    'name': 'Arugam Bay',
                    'flag': 'e/e6/Flag_of_Alaska.svg/43px-Flag_of_Alaska.svg.png'
                },
                {'name': 'Dambulla', 'flag': 'e/e6/Flag_of_Alaska.svg/43px-Flag_of_Alaska.svg.png'},
                {'name': 'Ella', 'flag': '4/46/Flag_of_Colorado.svg/45px-Flag_of_Colorado.svg.png'},
                {
                    'name': 'Galle',
                    'flag': '9/96/Flag_of_Connecticut.svg/39px-Flag_of_Connecticut.svg.png'
                },
                {'name': 'Hikkaduwa', 'flag': 'a/aa/Flag_of_Iowa.svg/44px-Flag_of_Iowa.svg.png'},
                {
                    'name': 'Kalpitiya',
                    'flag': '0/01/Flag_of_Illinois.svg/46px-Flag_of_Illinois.svg.png'
                },
                {
                    'name': 'Kandy',
                    'flag': '5/54/Flag_of_Georgia_%28U.S._state%29.svg/46px-Flag_of_Georgia_%28U.S._state%29.svg.png'
                },
                {
                    'name': 'Polunnaruwa',
                    'flag': 'b/bc/Flag_of_Wyoming.svg/43px-Flag_of_Wyoming.svg.png'
                },
                {'name':'Mirissa','flag':'e/ef/Flag_of_Hawaii.svg/46px-Flag_of_Hawaii.svg.png'},
                {'name':'Weligama','flag':'a/a4/Flag_of_Idaho.svg/38px-Flag_of_Idaho.svg.png'},

            ];


        }
    ])

    //
    .controller('PaymentCtrl', ['$scope',
        function ($scope) {
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
    ])

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
    })
;


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

