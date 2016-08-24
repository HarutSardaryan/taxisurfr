module.exports = function() {
    var contextpath = 'dashboard';
    var dashboard = './src/main/webapp/dashboard/';
    var client = './src/main/webapp/client/';
    var dashboardServer = './src/test/javascript/dashboard/server/';
    var clientServer = './src/test/javascript/client/server/';
    var dashboardApp = dashboard + 'app/';
    var clientApp = client + 'app/';
    var report = './report/';
    var root = './';
    var specRunnerFile = 'specs.html';
    var dashboardTemp = './.dashboardtmp/';
    var clientTemp = './.clienttmp/';
    var wiredep = require('wiredep');
    var bowerFiles = wiredep({devDependencies: true})['js'];
    var bower = {
        json: require('./bower.json'),
        directory: './bower_components/',
        ignorePath: '../../../..'
    };
    var nodeModules = 'node_modules';

    var config = {
        /**
         * File paths
         */
        // all javascript that we want to vet
        alljs: [
            './src/**/*.js',
            './*.js'
        ],
        contextpath: contextpath,
        buildDashboard: './build/dashboard/',
        buildClient: './build/client/',
        client: client,
        dashboard: dashboard,
        css: dashboardTemp + 'style.css',
        cssClient: clientTemp + 'style.css',
        fonts: bower.directory + 'font-awesome/fonts/**/*.*',
        html: dashboard + '**/*.html',
        dashboardHtmltemplates: dashboardApp + '**/*.html',
        clientHtmltemplates: clientApp + '**/*.html',
        dashboardImages: dashboard + 'images/**/*.*',
        clientImages: client + 'images/**/*.*',
        indexClient: client + 'index.html',
        indexDashboard: dashboard + 'index.html',
        clientIndex: client + 'index.html',
        // app js, with no specs
        js: [
            dashboardApp + '**/*.module.js',
            dashboardApp + '**/*.js',
            '!' + dashboardApp + '**/*.spec.js'
        ],
        jsClient: [
            clientApp + '**/*.module.js',
            clientApp + '**/*.js',
            '!' + clientApp + '**/*.spec.js'
        ],
        jsOrder: [
            '**/app.module.js',
            '**/*.module.js',
            '**/*.js'
        ],
        less: dashboard + 'styles/style.less',
        lessClient: client + 'styles/style.less',
        report: report,
        root: root,
        dashboardServer: dashboardServer,
        clientServer: clientServer,
        source: 'src/',
        stubsjs: [
            bower.directory + 'angular-mocks/angular-mocks.js',
            dashboard + 'stubs/**/*.js'
        ],
        dashboardTemp: dashboardTemp,
        clientTemp: clientTemp,

        /**
         * optimized files
         */
        optimized: {
            app: 'app.js',
            lib: 'lib.js'
        },

        /**
         * plato
         */
        plato: {js: dashboardApp + '**/*.js'},

        /**
         * browser sync
         */
        browserReloadDelay: 1000,

        /**
         * template cache
         */
        templateCache: {
            file: 'templates.js',
            options: {
                module: 'app.core',
                root: 'app/',
                standalone: false
            }
        },

        /**
         * template cache client
         */
        templateCacheClient: {
            file: 'templatesClient.js',
            options: {
                module: 'app.core',
                root: 'app/',
                standalone: false
            }
        },

        /**
         * Bower and NPM files
         */
        bower: bower,
        packages: [
            './package.json',
            './bower.json'
        ],

        /**
         * specs.html, our HTML spec runner
         */
        specRunner: dashboard + specRunnerFile,
        specRunnerFile: specRunnerFile,

        /**
         * The sequence of the injections into specs.html:
         *  1 testlibraries
         *      mocha setup
         *  2 bower
         *  3 js
         *  4 spechelpers
         *  5 specs
         *  6 templates
         */
        testlibraries: [
            nodeModules + '/mocha/mocha.js',
            nodeModules + '/chai/chai.js',
            nodeModules + '/sinon-chai/lib/sinon-chai.js'
        ],
        specHelpers: [dashboard + 'test-helpers/*.js'],
        specs: [dashboardApp + '**/*.spec.js'],
        serverIntegrationSpecs: [dashboard + '/tests/server-integration/**/*.spec.js'],

        /**
         * Node settings
         */
        nodeServerDashboard: dashboardServer + 'app.js',
        nodeServerClient: clientServer + 'app.js',
        defaultPort: '8001'
    };

    /**
     * wiredep and bower settings
     */
    config.getWiredepDefaultOptions = function() {
        var options = {
            bowerJson: config.bower.json,
            directory: config.bower.directory,
            ignorePath: config.bower.ignorePath
        };
        return options;
    };

    /**
     * karma settings
     */
    config.karma = getKarmaOptions();

    return config;

    ////////////////

    function getKarmaOptions() {
        var options = {
            files: [].concat(
                bowerFiles,
                config.specHelpers,
                dashboardApp + '**/*.module.js',
                dashboardApp + '**/*.js',
                dashboardTemp + config.templateCache.file,
                config.serverIntegrationSpecs
            ),
            exclude: [],
            coverage: {
                dir: report + 'coverage',
                reporters: [
                    // reporters not supporting the `file` property
                    {type: 'html', subdir: 'report-html'},
                    {type: 'lcov', subdir: 'report-lcov'},
                    {type: 'text-summary'} //, subdir: '.', file: 'text-summary.txt'}
                ]
            },
            preprocessors: {}
        };
        options.preprocessors[dashboardApp + '**/!(*.spec)+(.js)'] = ['coverage'];
        return options;
    }
};
