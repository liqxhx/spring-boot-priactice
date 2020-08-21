layui.define(["element", "jquery", "laypage", "laytpl", "layer", "upload"], function (exports) {
    var element = layui.element, jquery = layui.jquery, laypage = layui.laypage,
        laytpl = layui.laytpl, layer = layui.layer, upload = layui.upload;

    //执行一个laypage实例
    // laypage.render({
    //     elem: 'pager' //注意，这里的 test1 是 ID，不用加 # 号
    //     ,count: 50 //数据总数，从服务端得到
    // });


    function render(obj) {
        jquery.get(
            'user/mgmt/getlist',
            {page: obj.curr, limit: obj.limit},
            dto => {
                // laytpl
                var trTPL = jquery("#trTPL").html();
                laytpl(trTPL).render(
                dto.data,
                function (html) {
                    jquery('#trContainer').html(html);
                });
            });
    }

    jquery.get('user/mgmt/count', countDTO => {
        laypage.render({
            elem: 'pager' //注意，这里的 test1 是 ID，不用加 # 号
            , count: countDTO.count //数据总数，从服务端得到
            , limit: 20
            , jump: function (obj, first) {
                render(obj);
            }
        });
    });

    jquery('#trContainer').on('click', '.deleteItem', function(){
       layer.confirm('确定要删除吗?', {icon:3, title: '提示', closeBtn:0}, function (index) {

           layer.close(index);
       })
    });

    jquery('#trContainer').on('click', '.detailItem', function(){
        let that = jquery(this);
        jquery.get('/user/mgmt/detail/'+that.attr('data-id'), dto => {
            laytpl(jquery("#detailTPL").html()).render(dto.data, function (html) {jquery('#detailContainer').html(html);});
            var index = layer.open({
                type: 1, // 0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                content: jquery('#detailContainer'),
                area: ['600px', '300px'],
                title: "详情信息",
                fixed: true,
                resize: false,
                anim: 0,
                shade: [0.8, '#393D49'],
                closeBtn: 0, // 不显示closeBtn
                btn: ['保存', '重置', '关闭'],
                btnAlign:'c',
                btn1:function(index, layero) {
                    console.log('save')
                    layer.close(index);
                },
                btn2:function(e) {
                    console.log('cancle')
                    // 还原
                    laytpl(jquery("#detailTPL").html()).render(dto.data, function (html) {jquery('#detailContainer').html(html);});
                    return false; // 可禁止点击该按钮关闭
                },
                btn3:function() {
                    console.log('close')

                },
            });

        });
    });

    jquery('#addItemBtn').click(function(){
        var index = layer.open({
            type:1,
            content: jquery('#addItemContainer'),
            area: ['600px', '500px'],
            title: "添加",
            fixed: true,
            resize: false,
            anim: 0,
            shade: [0.8, '#393D49'],
            closeBtn: 0, // 不显示closeBtn
            btn: ['提交', '重置', '关闭'],
            btnAlign:'c',
            btn1:function(index, layero) {
                console.log('save')
                layer.close(index);
            },
            btn2:function(e) {
                console.log('cancle')
                // 还原
                // laytpl(jquery("#addItemTPL").html()).render({}, function (html) {jquery('#addItemContainer').html(html);});
                return false; // 可禁止点击该按钮关闭
            },
            btn3:function() {
                console.log('close')

            },
        });
    });


    //普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        ,url: 'https://httpbin.org/post' //改成您自己的上传接口
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    // 输出
    exports('main', {});
});