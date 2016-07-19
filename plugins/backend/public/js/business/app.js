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
        loadHmtlByJs: function(url) {
            var self = this;
            var urlArr = url.split('#');
            var jsUrl = urlArr[0];
            var paraUrl = urlArr[1];
            var paraJson = $.queryToJson(paraUrl);
            if(this.lruCache.get(jsUrl) && !!!paraJson['renew']){ //在缓存内获取到
                var view = this.lruCache.get(jsUrl);
                $("#page-wrapper").html(view.el);
                view.delegateEvents();
            }else {
                require([jsUrl], function(index){
                    var view = new index($.extend({method: url}, paraJson));
                    self.lruCache.set(jsUrl, view);
                    self.showView(view);
                })
            }

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