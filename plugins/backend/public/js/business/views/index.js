/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'component', 'js/business/views/dash'], function(Backbone, Component, Dash){
    var view = Backbone.View.extend({
        initialize: function(){
            this.tabs = undefined;
            this.tableRef = undefined; //table引用
            this.seaFormRef =  undefined; //搜索框引用
            this.tabs = undefined;
            this.compoment = new Component(this);
            this.render();
        },
        render: function(){
            var $table = this.compoment.geneTable(this.tableParams(), this.searParams());
            this.tabs = this.compoment.geneTab([{
                title: '标题档',
                content: this.compoment.genePanel(undefined, $table.geneTable()),
                active: true
            }]);
            this.compoment
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
            this.tabs.addTab({
                title: '新增',
                content: new Dash(undefined, this).$el.children()
            })
        },
        searParams : function() {
            var searParams = {
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
                    callback: 'search'
                },{
                    title: '新增',
                    callback: $.proxy(this.modify, this)
                }]
            };
            return searParams;
        },
        tableParams : function(){
            var tableParams = {
                "ajax": {
                    url: '/backend/demoData',
                    type: 'POST'
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
            return tableParams;
        }
    });
    return view;
});