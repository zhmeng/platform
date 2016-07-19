/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var view = Backbone.View.extend({
        initialize: function(){
            this.content = new Component(this);
            this.tableRef = undefined;
            this.seaFormRef = {};
            this.tabs = undefined;
            this.render();
        },
        showHello: function(d){
            console.log('Hello');
            console.log(d);
        },
        doSearch: function(){
            this.tableRef.ajax.reload();
        },
        render: function(){
            var self = this;
            var formParams = {
                filters:[{
                    title: '名称',
                    name: 'name'
                },{
                    title: '地址',
                    name: 'address'
                }],
                btns: [{
                    title: '查询',
                    class: 'btn-primary',
                    callback: $.proxy(self.doSearch, self)
                },{
                    title: '新增'
                }]
            };
            var tableParams = {
                "ajax": {
                    url: '/backend/demoData',
                    type: 'POST',
                    data: function(d){
                        $.extend(d, self.seaFormRef.serializeJson());
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

            $tabs = this.content.geneTab([{
                title: '标题档',
                content: 'asdfsdf',
                active: true
            },{
                title: '好学生',
                content: '我是一个好学生'
            },{
                title: '第三者',
                content: '我是第三真'
            }]);
            this.tabs = $tabs;
            this.content.appendNative($tabs.full()).rebuild();
            var $button = $('<button class="btn btn-default">ADD</button>');
            $button.click(function(){
                self.tabs.addTab({
                    title: '第五个',
                    content: '<div>GOGOGOG</div>'
                })
            });
            $tabs.add({
                title: '第四者',
                content: $button
            });
            this.seaFormRef = $searchContent;
            this.tableRef = $table.nDataTable(tableParams);
        }
    });
    return view;
});