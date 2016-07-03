/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'text!js/business/templates/first.html'], function(Backbone, firstFtl){
    var view = Backbone.View.extend({
        events: {
            'click #firstBtn': 'firstBtn'
        },
        initialize: function(){
            this.$el.empty().html(firstFtl);
            this.delegateEvents();
        },
        firstBtn: function(){
            alert('firstBtn');
        },
        render: function(){}
    });
    return view;
});