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
    $.Stack = Stack;
    $.LRUCache = LRUCache
});