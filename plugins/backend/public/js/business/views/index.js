/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var view = Backbone.View.extend({
        initialize: function(){
            this.content = new Component(this);
            this.render();
        },
        showHello: function(d){
            console.log('Hello');
            console.log(d);
        },
        render: function(){
            var self = this;
            var formParams = {
                filters:[{
                    name: '名称'
                },{
                    name: '地址'
                }],
                btns: [{
                    name: '查询'
                }]
            };
            var tableParams = {
                "ajax": {
                    url: '/backend/demoData',
                    type: 'POST',
                    data: function(d){
                        console.log('VV');
                    }
                },
                columns: [{
                        title: "编号",
                        data: "no"
                    },{
                        title: "名称",
                        data: "name"
                    },{
                        title: "地区码",
                        data: "areacode",
                        render: function(d, type, full){
                            return "<span style='color:red;'>" + d + "</span>";
                        }
                    },{
                        title: '操作',
                        data: null,
                        render: function(data, type, full){
                            return '<button>' + 'HELLO' + '</button>';
                        },
                        createdCell: function (td, cellData, rowData, row, col) {
                            $(td).children('button').on('click', $.proxy(self.showHello, self, rowData));
                        }
                    }
                ]
            };
            var $searchContent = this.content.geneForm(formParams);
            var $table = this.content.geneTable();
            var $fullTable = this.content.geneFullTable($searchContent, $table);
            this.content
                .appendTitle('H3 TITLE')
                .appendPanel($("<div>HELLO</div>"), $fullTable)
                .build();
            $table.nDataTable(tableParams);
        }
    });
    return view;
});