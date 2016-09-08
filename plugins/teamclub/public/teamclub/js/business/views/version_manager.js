/**
 * Created by wjr on 16-9-8.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var index = Backbone.View.extend({
        initialize: function(){
            this.$el.append('<div>HELLO</div>');
        },
        render: function(){

        }
    });
    return index;
});