/**
 * Created by Administrator on 2016/7/5.
 */
define([
    'underscore',
    'text!js/business/templates/content/title.html',
    'text!js/business/templates/content/panel.html'
], function(_, TitleTpl, panelTpl){
    var view = Backbone.View.extend({
        titleTemplate: _.template(TitleTpl),
        panelTemplate: _.template(panelTpl),
        initialize: function(owner){
            this.tmpContent = $("<div></div>");
            this.owner = owner;
        },
        setTitle: function(params){
            var title = this.titleTemplate(params);
            this.tmpContent.append(title);
            return this;
        },
        setPanel: function(params){
            var panel = this.panelTemplate(params);
            this.tmpContent.append(panel);
            return this;
        },
        build: function(){
            this.owner.$el.append(this.tmpContent);
            this.tmpContent = $('<div></div>');
        }
    });
    return view;
});