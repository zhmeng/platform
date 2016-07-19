/**
 * Created by Administrator on 2016/7/5.
 */
define([
    'jquery',
    'underscore',
    'datatables'
], function($, _){
    var Tab = Backbone.Base.extend({
        initialize: function(params){
            this.$top = $('<ul class="nav nav-tabs">');
            this.$bottom = $('<div class="tab-content">');
            this.tabs = {};
            this.contents = {};
            var self = this;
            _.each(params, function(d, idx) {
                self.add(d);
            });
            this.$full = $('<div>');
            this.$full.append(this.$top);
            this.$full.append(this.$bottom);
        },
        full: function(){
            return this.$full;
        },
        addTab: function(params){
            var idx = this.add(params);
            this.tabs[idx].find('a').tab('show');
        },
        closeTab: function(idx){
            this.remove(idx);
        },
        add: function(params){
            var self = this;
            var id = _.uniqueId("tab");
            var active = params['active'] == true ? 'active' : '';
            var panActive = params['active'] == true ? 'active in' : '';
            var $li = $('<li class="' + active + '"><a href="#'+ id +'" data-toggle="tab">' + params['title'] + '</a></li>');
            $li.on('click', function(e){
                var idx = $(e.currentTarget).children('a').attr('href');
                self.remove(idx);
            });
            var $con = $('<div class="tab-pane fade '+ panActive +'" id="' + id + '"></div>');
            $con.html(params['content']);
            this.tabs[id] = $li;
            this.contents[id] = $con;
            self.$top.append($li);
            self.$bottom.append($con);
            return id;
        },
        remove: function(idx){
            var self = this;
            idx = idx.substring(1);
            _.each(this.tabs, function(v, k){
               if(k.length > idx.length || k > idx){
                   v.remove();
                   delete self.tabs[k];
               }
            });
            _.each(this.contents, function(v, k){
               if(k.length > idx.length || k > idx){
                   v.remove();
                   delete self.tabs[k];
               }
            });
        }
    });
    var base = Backbone.Base.extend({
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
            var $e = $('<div class="row"><div class="col-md-12"><h3>' +　title + '</h3></div></div>');
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
        geneFullTable: function($row1, $row2){
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
            var $group = $('<div class="input-group"></div>');
            $group.append($('<span class="input-group-addon">'+ param['title'] +'</span>'));
            if(param['type'] == undefined || param['type'] == 'text'){
                $group.append($('<input type="text" name="' + param['name'] + '" class="form-control" />'));
            }
            $e.append($group);
            return $e;
        },
        geneBtn : function(param) {
            if(param['type'] == 'button') {
                var clazz = param['class'] == undefined ? 'btn-default' : param['class'];
                var $btn = $('<button type="submit" class="btn ' + clazz + '" style="min-width: 80px; margin-right: 10px; margin-bottom: 10px;">'+ param['title'] +'</button>');
                $btn.on('click', param.callback);
                return $btn;
            }
        },
        geneForm: function(params) {
            var $form = $('<div></div>');
            var $tmp = $('<div class="row"></div>');
            for(var i = 0 ; i < params.filters.length ; i++){
                $tmp.append(this.geneSingle(params.filters[i]));
            }
            $form.append($tmp);
            var $btns = $('<div class="row col-md-12">');
            for(var j = 0 ; j < params.btns.length; j++){
                params.btns[j]['type'] = 'button';
                $btns.append(this.geneBtn(params.btns[j]));
            }
            $form.append($btns);
            return $form;
        },
        appendForm: function(params){
            var $form = this.geneForm(params);
            this.tmpContent.append($form);
            return this;
        },
        geneTab: function(params){
            var tab = new Tab(params);
            return tab;
        },
        build: function(){
            this.owner.$el.append(this.tmpContent);
            this.tmpContent = $('<div></div>');
        },
        rebuild: function(){
            this.owner.$el.empty();
            this.owner.$el.append(this.tmpContent);
            this.tmpContent = $('<div>');
        }
    });
    return base;
});