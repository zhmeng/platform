define(['backbone', 'jquery', 'common'], function(Backbone, $, Common){
    var app = {
        currentView: null,
        router: null,
        stack: [],
        init: function(){
            console.log('app init');
        },
        showView: function(view, params){
            if (this.currentView && params['keep'] == undefined){
                this.currentView.close();
            }else{

            }
            this.currentView = view;
            $("#page-wrapper").html(this.currentView.el);
        },
        loadHmtlByJs: function(url) {
            var self = this;
            var urlArr = url.split('#');
            var jsUrl = urlArr[0];
            var paraUrl = urlArr[1];
            var paraJson = $.queryToJson(paraUrl);
            require([jsUrl], function(index){
                self.showView(new index($.extend({method: url}, paraJson)), paraJson);
            });
        }
    };
    var Router = Backbone.Router.extend({
        routes: {
            "*actions": "defaultRoute"
        }
    });
    app.router = new Router();
    app.router.on('route:defaultRoute', function(actions){
        if(actions !== undefined && actions !== null){
            app.loadHmtlByJs(actions);
        }
    });
    Backbone.history.start();
    return app;
});