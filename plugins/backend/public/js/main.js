(function () {
    requirejs.config({
        baseUrl: '/assets',
        paths: {
            'jquery': 'bower_components/jquery/dist/jquery.min',
            'bootstrap': 'bower_components/bootstrap/dist/js/bootstrap.min',
            'metisMenu': 'bower_components/metisMenu/dist/metisMenu.min',
            'datatables': 'bower_components/datatables/media/js/jquery.dataTables',
            'datatables.bootstrap': 'bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap',
            'datatables.responsive': 'bower_components/datatables-responsive/js/dataTables.responsive',
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
            'backbone': ['jquery', 'underscore'],
            'dataTables.bootstrap': ['datatables'],
            'dataTables.responsive': ['datatables']
        }
    });
    require(['jquery', 'underscore', 'backbone', 'common' , 'bootstrap', 'metisMenu', 'js/business/app',
        'datatables', 'datatables.bootstrap', 'datatables.responsive'], function(a, b, c, d, e, f, App) {
        App.init();
    });
})();