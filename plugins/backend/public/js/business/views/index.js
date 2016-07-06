/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var view = Backbone.View.extend({
        initialize: function(){
            this.content = new Component(this);
            this.render();
        },
        render: function(){
            var formParams = [{
                name: '名称',
                type: 'text'
            },{
                name: '地址',
                type: 'text'
            }];
            var tableParams = {
                "ajax": '/backend/demoD',
                columns: [
                    { title: "Name" },
                    { title: "Position" },
                    { title: "Office" },
                    { title: "Extn." },
                    { title: "Start date" },
                    { title: "Salary" }
                ]
            };
            var $searchContent = this.content.geneForm(formParams);
            var $table = this.content.geneTable(tableParams);
            var $fullTable = this.content.geneFullTable($searchContent, $table);
            this.content
                .appendTitle("哈哈")
                .appendPanel($("<div>HELLO</div>"), $fullTable)
                .build();
        }
    });
    return view;
});