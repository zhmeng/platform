/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var view = Backbone.View.extend({
        initialize: function(){
            var content = new Component(this)
            content.setTitle({}).setPanel({}).build();
        },
        render: function(){}
    });
    return view;
});