/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone'], function(Backbone){
    var view = Backbone.View.extend({
        initialize: function(){
            this.$el.empty().html('<div>HELLO I AM INDEX</div>');
        },
        render: function(){}
    });
    return view;
});