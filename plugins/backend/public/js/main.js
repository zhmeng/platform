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
            'underscore': 'bower_components/underscore/underscore'
        },
        shim: {
            'bootstrap': ['jquery'],
            'metisMenu': ['jquery'],
            'sbAdmin': ['jquery', 'metisMenu'],
            'backbone': ['jquery', 'underscore']
        }
    });
    require(['js/business/app','backbone', 'underscore', 'jquery', 'bootstrap', 'metisMenu', 'sbAdmin'], function(App,Backbone, _ , $) {
        App.init();
    });
})();