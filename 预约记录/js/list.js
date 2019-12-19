layui.use('table', function(){
    var table = layui.table;
    //方法级渲染
    table.render({
        elem: '#LAY_table_user'
        ,url: '/Record/products.do'
        ,parseData:function (res) {
            return{
                "code": 0, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res //解析数据列表
            };
        }
        ,cols: [[
            {field:'oid', title: '序号',minWidth:'3%'}
            ,{field:'oname', title: '用户名',minWidth:'3%'}
            ,{field:'ophone', title: '电话',minWidth:'3%'}
            ,{field:'opdata', title: '预约时间' , minWidth:'3%'}
            ,{field:'optime', title: '时间段', sminWidth:'3%'}
            ,{field:'hid', title: '房源', minWidth:'3%'}
        ]]
        ,id: 'testReload'
        ,height: 500
    });

    var $ = layui.$, active = {
        reload: function(){
            var demoReload = $('#demoReload');

            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        id: demoReload.val()
                    }
                }
            }, 'data');
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});

