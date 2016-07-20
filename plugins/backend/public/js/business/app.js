define(['backbone', 'jquery', 'common'], function(Backbone, $){
    var app = {
        currentView: null,
        router: null,
        lruCache: new $.LRUCache(),
        init: function(){
            console.log('app init');
        },
        showView: function(view){
            this.currentView = view;
            $("#page-wrapper").html(this.currentView.el);
        },
        view404: Backbone.View.extend({
            initialize: function(err){
                this.$el.html(err.message);
            }
        }),
        loadHmtlByJs: function(url) {
            var self = this;
            var urlArr = url.split('#');
            var jsUrl = urlArr[0];
            var paraUrl = urlArr[1];
            var paraJson = $.queryToJson(paraUrl);

            require([jsUrl], function(index){
                var view = new index($.extend({method: url}, paraJson));
                self.showView(view);
            }, function(err){
                var view404 = new self.view404(err);
                self.showView(view404);
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