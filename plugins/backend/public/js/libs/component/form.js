/**
 * Created by wjr on 16-7-6.
 */
define(['jquery','underscore','common'], function($, _) {
    var Form = Backbone.Base.extend({
        initialize: function (params) {
            var self = this;
            this.$row = $('<form role="form">');
            var i = 0 ;
            _.each(params, function(param){
                console.log(param);
                var title = param.title || '无题';
                var type = param.type || 'text';
                var name = param.name ;
                self.$row.append((self.geneSingle(i++, title, type, name)));
            });
            console.log(self.$row);
        },
        geneSingle: function(i, title, type, name){
            if(i % 2 == 0 ) {
                var clazz = 'col-md-5'
            }else {
                var clazz = 'col-md-5 col-md-offset-2'
            }
            var $formgroup = $('<div class="form-group ' + clazz + '">');
            var $formG = $('<div class="input-group"></div>');
            $formgroup.append($formG);
            var $inputgroupaddon = $('<span class="input-group-addon"></span>');
            $inputgroupaddon.text(title);
            var $input = $('<input class="form-control">');
            $input.attr('type', type);
            $input.attr('name', name);
            $formG.append($inputgroupaddon);
            $formG.append($input);
            return $formgroup;
        },
        form: function(){
            var $tmp = $('<div class="row col-md-12">');
            $tmp.append(this.$row);
            return $tmp;
        }
    });
    return Form;
});