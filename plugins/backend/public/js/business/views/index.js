/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'component', 'js/business/views/dash'], function(Backbone, Component, Dash){
    var view = Backbone.View.extend({
        tabs: undefined,
        tableRef: undefined, //table引用
        seaFormRef: undefined, //搜索框引用
        initialize: function(){
            this.content = new Component(this);
            this.render();
        },
        render: function(){
            var self = this;
            this.seaFormRef = this.geneForm();
            var $table = this.content.geneTable();
            var $fullTable = this.content.geneFullTable(this.seaFormRef, $table);
            this.rebuildTable($table);
            this.tabs = this.content.geneTab([{
                title: '标题档',
                content: this.content.genePanel("vvv", $fullTable),
                active: true
            }]);
            this.content
                .appendNative(this.tabs.full())
                .build();

        },
        setVal: function(){
            this.$('input[name=name]').val('vasdf');
            this.doSearch();
        },
        showHello: function(d){
            console.log('Hello');
            console.log(d);
        },
        doSearch: function(){
            this.tableRef.ajax.reload();
        },
        modify: function(){
            var self = this;
            this.tabs.addTab({
                title: '新增',
                content: new Dash(undefined, this).el
            })
        },
        geneForm : function(){
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
                    callback: $.proxy(this.doSearch, this)
                },{
                    title: '新增',
                    callback: $.proxy(this.modify, this)
                }]
            };
            var $searchContent = this.content.geneForm(formParams);
            return $searchContent;
        },
        rebuildTable: function($table){
            var self = this;
            var tableParams =　{
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
                }]
            };
            this.tableRef = $table.nDataTable(tableParams);
        }
    });
    return view;
});