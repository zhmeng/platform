/**
 * Created by Administrator on 2016/7/5.
 */
define([
    'jquery',
    'underscore',
    'datatables'
], function($, _){
    var view = Backbone.View.extend({
        initialize: function(owner){
            this.tmpContent = $("<div></div>");
            this.owner = owner;
        },
        //直接添加
        appendNative: function($ele) {
            this.tmpContent.append($ele);
            return this;
        },
        //生成标题
        appendTitle: function(title){
            var $e = $('<div class="row"><div class="col-md-12"><h3 class="page-header">' +　title + '</h3></div></div>');
            this.tmpContent.append($e);
            return this;
        },
        //生成面板
        genePanel: function($head, $body){
            var panel = $('<div class="panel panel-default"></div>');
            var panelHead = $('<div class="panel-heading"></div>');
            var panelBody = $('<div class="panel-body"></div>');
            panelHead.append($head);
            panelBody.append($body);
            panel.append(panelHead).append(panelBody);
            return panel;
        },
        appendPanel: function($head, $body){
            var $panel = this.genePanel($head, $body);
            this.tmpContent.append($panel);
            return this;
        },
        //生成Table
        geneTable: function(){
            var table = $('<table class="table table-striped table-bordered table-hover" width="100%"></table>');
            return table;
        },
        appendTable: function(params){
            var $table = this.geneTable(params);
            this.tmpContent.append($table);
            return this;
        },
        geneFullTable: function($row1, $row2, $row3){
            var $full = $('<div>');
            $full.append($row1).append($row2);
            return $full;
        },
        appendFullTable: function($row1, $row2, $row3){
            var $full = this.geneFullTable($row1, $row2, $row3);
            this.tmpContent.append($full);
            return this;
        },
        geneSingle: function(param){
            var $e = $('<div class="col-md-3 form-group"></div>');
            if(param['type'] == 'button') {
                $e.append($('<button type="submit" class="btn btn-default" style="min-width: 80px;">'+ param['name'] +'</button>'));
                return $e;
            }
            var $group = $('<div class="input-group"></div>');
            $group.append($('<span class="input-group-addon">'+ param['name'] +'</span>'));
            if(param['type'] == undefined || param['type'] == 'text'){
                $group.append($('<input type="text" class="form-control" />'));
            }
            $e.append($group);
            return $e;
        },
        geneForm: function(params) {
            var $form = $('<div></div>');
            var $tmp = $('<div class="row"></div>');
            for(var i = 0 ; i < params.filters.length ; i++){
                $tmp.append(this.geneSingle(params.filters[i]));
            }
            //$form.append($tmp);
            //var $btns = $('<div class="row"></div>');
            for(var j = 0 ; j < params.btns.length; j++){
                params.btns[j]['type'] = 'button';
                $tmp.append(this.geneSingle(params.btns[j]));
            }
            $form.append($tmp);
            return $form;
        },
        appendForm: function(params){
            var $form = this.geneForm(params);
            this.tmpContent.append($form);
            return this;
        },
        build: function(){
            this.owner.$el.append(this.tmpContent);
            this.tmpContent = $('<div></div>');
        }
    });
    return view;
});