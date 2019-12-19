layui.use('table', function(){
    var table = layui.table;

    //方法级渲染
    table.render({
        elem: '#LAY_table_user1'
        ,url: '/Record/products.do?key=1'
        ,parseData:function (res) {
            return{
                "code": 0, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res //解析数据列表
            };
        }
        ,toolbar: '#toolbarDemo'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'oid', title: '序号',minWidth:'15%'}
            ,{field:'oname', title: '用户名',minWidth:'10%'}
            ,{field:'ophone', title: '电话',minWidth:'3%'}
            ,{field:'opdata', title: '预约时间' ,minWidth:'3%'}
            ,{field:'optime', title: '时间段',minWidth:'3%'}
            ,{field:'hid', title: '房源',minWidth:'3%'}
        ]]
        ,page: true
        ,limit:7 //默认十条数据一页
        ,id:'testReload'
        ,height: 400
    });

    //头工具栏事件
    table.on('toolbar(user)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                var c = JSON.stringify(data);
                if (confirm("是否确认取消。")) {
                    $.ajax('/Record/products.do?key=2',{
                        type: 'POST',
                        url: '/Record/products.do?key=2',
                        dataType: 'json',
                        data: {c},
                        async: false,
                        success: function (response) {
                        }
                    });
                    location.reload();
                }else {
                    alert(2);
                }
        };
    });
});
