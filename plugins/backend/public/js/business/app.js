define(['backbone'], function(Backbone){
    var loadHmtlByJs = function(url) {
        require([url], function(index){
            new index({el : $('#page-wrapper')});
        });
    };
    var app = {
        views: {},
        router: null,
        init: function(){
            console.log('app init');
        }
    };
    var Router = Backbone.Router.extend({
        routes: {
            "*actions": "defaultRoute"
        }
    });
    app.router = new Router();
    app.router.on('route:defaultRoute', function(actions){
        console.log(actions);
        loadHmtlByJs(actions);
    });
    Backbone.history.start();
    return app;
});