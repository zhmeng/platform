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
        geneTable: function(params){
            var table = $('<table class="display table table-striped table-bordered table-hover dtr-inline" style="width: 100%"></table>');
            table.DataTable(params);
            return table;
        },
        appendTable: function(params){
            var $table = this.geneTable(params);
            this.tmpContent.append($table);
            return this;
        },
        getRowDiv: function(){
            return $('<div class="row"></div>');
        },
        geneFullTable: function($row1, $row2, $row3){
            var $full = $('<div>');
            $full.append(this.getRowDiv().append($row1));
            $full.append(this.getRowDiv().append($row2));
            $full.append(this.getRowDiv().append($row3));
            return $full;
        },
        appendFullTable: function($row1, $row2, $row3){
            var $full = this.geneFullTable($row1, $row2, $row3);
            this.tmpContent.append($full);
            return this;
        },
        geneSingle: function(param){
            var $e = $('<div class="col-md-3"></div>');
            var $group = $('<div class="input-group"></div>');
            $group.append($('<span class="input-group-addon">'+ param['name'] +'</span>'));
            if(param['type'] == 'text'){
                $group.append($('<input type="text" class="form-control" />'));
            }
            $e.append($group);
            return $e;
        },
        geneForm: function(params) {
            var $form = $('<div></div>');
            for(var i = 0 ; i < params.length ; i++){
                $form.append(this.geneSingle(params[i]));
            }
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