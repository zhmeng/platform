/**
 * Created by wjr on 16-7-20.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var app = Backbone.View.extend({
        events: {
            'click #btn': 'showClick'
        },
        showClick: function(){
            this.tabs.closeCurTab();
        },
        initialize: function(a, parent){
            this.parent = parent;
            this.tabs = this.parent.tabs;
            this.component = new Component(this);
            this.render();
        },
        formParams: function(){
            var formParams = [{
                title: '编号',
                name: 'no'
            },{
                title: '名称',
                name: 'name'
            },{
                title: '地区码',
                name: 'areacode'
            },{
                title: '地址',
                name: 'address'
            }];
            return formParams;
        },
        render: function(){
            var form = this.component.geneForm(this.formParams());
            this.component
                .appendPanel('HAHA', form.form())
                .build();
        }
    });
    return app;
});