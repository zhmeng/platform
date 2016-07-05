(function () {
    requirejs.config({
        baseUrl: '/assets',
        paths: {
            'jquery': 'bower_components/jquery/dist/jquery.min',
            'bootstrap': 'bower_components/bootstrap/dist/js/bootstrap.min',
            'metisMenu': 'bower_components/metisMenu/dist/metisMenu.min',
            'sbAdmin': 'js/thrid/sb-admin-2',
            'backbone': 'bower_components/backbone/backbone',
            'text': 'bower_components/text/text',
            'underscore': 'bower_components/underscore/underscore',
            'common': 'js/libs/common',
            'component': 'js/libs/component'
        },
        shim: {
            'bootstrap': ['jquery'],
            'metisMenu': ['jquery'],
            'sbAdmin': ['jquery', 'metisMenu'],
            'backbone': ['jquery', 'underscore']
        }
    });
    require(['jquery', 'underscore', 'backbone', 'common' , 'bootstrap', 'metisMenu', 'js/business/app'], function(a, b, c, d, e, f, App) {
        App.init();
    });
})();