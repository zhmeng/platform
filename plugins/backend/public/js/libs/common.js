/**
 * Created by wjr on 16-7-4.
 */
define(['jquery', 'backbone', 'js/libs/stack', 'js/libs/lru'],function($, Backbone, Stack){
    $.fn.serializeEl = function(){
        var param = {};
        var input = this.find('input,select');
        input.each(function(i,item){
            item = $(item);
            param[item.attr('name')] = item.val();
        });
        return param;
    };
    $.postJSON = function(url, data, success, failure) {
        return $.ajax({
            type: "POST",
            url: url,
            data: data,
            dataType: "json",
            success: success
        });
    };
    Backbone.View.prototype.close = function(){
        this.remove();
        this.unbind();
        if(this.onClose){
            this.onClose();
        }
    };
    $.queryToJson = function(queryString) {
        if(queryString == undefined || queryString == ""){
            return {};
        }
        var j, q;
        q = queryString.replace(/\?/, "").split("&");
        j = {};
        $.each(q, function(i, arr) {
            arr = arr.split('=');
            return j[arr[0]] = arr[1];
        });
        return j;
    };
    $.fn.nDataTable = function(params){
        var baseParam = {
            "processing": true,
            "serverSide": true,
            "bLengthChange": false,
            iDisplayLength :10,
            bFilter: false,
            "bSort": false,
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                },
                "sZeroRecords": "没有检索到数据",
                "sProcessing": "<img src='/assets/images/loading.gif' />"
            }
        };
        params = $.extend(baseParam, params);
        this.DataTable(params);
    };
    $.Stack = Stack;
    $.LRUCache = LRUCache
});