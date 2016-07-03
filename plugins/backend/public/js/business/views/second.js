/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'text!js/business/templates/second.html'], function(Backbone, ftl){
    var view = Backbone.View.extend({
        events: {
            'click #secondBtn': 'secondBtn'
        },
        initialize: function(){
            this.$el.empty().html(ftl);
            this.delegateEvents();
        },
        secondBtn: function(){
            alert('secondBtn..');
        },
        render: function(){}
    });
    return view;
});