layui.define(['table', 'jquery'/*, 'laytpl', 'layer'*/], function (exports) {
    var table = layui.table
        ,jquery = layui.jquery
        // ,laytpl = layui.laytpl
        // ,layer = layui.layer;
    var obj = {
        ping: function(str) {
            alert('pong '+str);
        }
    };

    table.render({
        elem: '#userTable',
        url: '/user/mgmt/list',
        // page: true,
        method: 'get',

        page: {
            limit: 20, // 第页显示1条
            limits: [20, 40, 80], // 可选择每页多少条
            first: "<<",
            last: ">>",
            prev: "<em><</em>",
            next: "<em>></em>",
            layout: ['first', 'prev', 'page', 'last', 'next', 'count', 'limit', 'skip', 'refresh'] // 自定义分页布局
        },

        toolbar: true,

       // data: [{userId:0,username:'', password:'', ops:"操作" }],

        parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            }
        },
        cols: [[
            {field:'userId', titile:"用户编号", sort: true},
            {field:'userName', titile:"用户姓名", sort: true},
            {field:'password', titile:"用户密码"},
            {field:'ops', title:"操作", toolbar:"#userTableToolBar"}
        ]],

    });

    // 事件注册
    // 表格的lay-filter
    table.on('tool(userTable)', function(obj) {
        var data = obj.data; // 当前行
        // toolbar事件
        var event = obj.event;

        if(event === 'del') {
            layer.confirm("确定要删除?", function(index){
                obj.del();
                layer.close(index);
            })
        } else if(event === 'edit') {
            obj.update({
                userId: 0,
                userName: "liqh",
                password: "***",

            });
        }

    });


    console.log(jquery);

    // 输出
    exports('users', obj);
});