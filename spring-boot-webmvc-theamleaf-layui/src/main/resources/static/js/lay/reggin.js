//Demo
layui.use(['form','laydate', 'element'], function(form, laydate, element){
    // var form = layui.form
    //     , laydate = layui.laydate;

    //常规用法
    laydate.render({
        elem: '#birthDate'
    });

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });

    //监听折叠
    element.on('collapse(attachemntInfoPanel)', function(data){
        layer.msg('展开状态：'+ data.show);
    });
});