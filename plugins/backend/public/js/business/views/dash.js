/**
 * Created by wjr on 16-7-20.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var app = Backbone.View.extend({
        events: {
            'click #btn': 'showClick'
        },
        showClick: function(){
            console.log("hello");
        },
        initialize: function(a, parent){
            this.parent = parent;
            this.$el.html('<button id="btn">CLICK</button>');
        }
    });
    return app;
});