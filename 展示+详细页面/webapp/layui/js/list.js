//注意：导航 依赖 element 模块，否则无法进行功能性操作
var pageSize = 10;
var num = 1;
layui.use(['flow','element'], function(){
    var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
    var flow = layui.flow;
    var address="全部";
    var sort=0;
    var flag = true;
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    element.on('nav(demo)', function(elem){
        // console.log(elem); //得到当前点击的DOM对象
        var text = elem.attr('data-text');
        if (text != undefined ){
            address = text;
            sort=0;
        }

        var sort1 = elem.attr('data-id');
        if (sort1 != undefined ){
            sort = sort1;
        }

        $("#LAY_demo1").remove();
        $("#qw").append('<ul id="LAY_demo1"></ul>');
        flow.load({
            elem: '#LAY_demo1', //指定列表容器
            end:"没有更多了",
            done: function(page, next){
                var lis = [];
                //到达临界点（默认滚动触发），触发下一页
                //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                var params = {
                    currPage:page,
                    pageSize:pageSize,
                    area:address,
                    sort:sort
                };
                $.ajax('/Car/list.do',{
                    type: 'POST',
                    url: '/Car/list.do',
                    dataType: 'json',
                    data: params,
                    async: false,
                    success: function (response) {
                        layui.each(response.data, function (index, item) {
                            lis.push('<li>' +
                                '<div style="height: 175px" class="layui-row">'+
                                    '<div style="height:100% margin: 0 auto; text-align: center" class="layui-col-xs8 ">' +
                                        '<img src="'+item.hphoto+'" width="90%" height="100%">'+
                                    '</div>'+
                                    '<div style="float:right; height: 100%" class="layui-col-xs4">'+
                                        '<div style=" height:33%">'+
                                            '<img src="img/必看.png" width="49px" height="16px">'+'<br>'+'<br>'+
                                            '<a href="/Car/getServlet?id='+item.hid+'">' +
                                            '<span style="font-size:15px">'+item.haddress+'</span>'+
                                                "——"+
                                            '<span>'+item.hid+'</span>'+
                                        '</div>'+
                                        '<div style="height: 65%">'+
                                            '<div style="height: 33%">' +
                                                '<span style="font-size:10px">面积:</span>'+
                                                '<span>'+item.harea+"m²"+'</span>'+
                                            '</div>'+ '</br>'+
                                            '<div style="height: 32%">' +
                                                '<span style="font-size:10px ">价格:</span>'+
                                                '<span style="color: red">'+item.hmoney+"万"+'</span>'+
                                            '</div>'+
                                            '<div>' +
                                                '<span>预约量:</span>'+
                                                '<span>'+item.reservations+'</span>'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>'+
                                '<hr class="layui-bg-black">'+
                                '</li>');
                            //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                            //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多

                        });
                        next(lis.join(''), page < response.totalCount); //假设总页数为 10
                    }
                });
            }
        });
    });

    if (flag){
        flow.load({
            elem: '#LAY_demo1', //指定列表容器
            end:"没有更多了",
            done: function(page, next){
                var lis = [];
                //到达临界点（默认滚动触发），触发下一页
                //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                var params = {
                    currPage:page,
                    pageSize:pageSize,
                    area:address,
                    sort:sort
                };
                $.ajax('/Car/list.do',{
                    type: 'POST',
                    url: '/Car/list.do',
                    dataType: 'json',
                    data: params,
                    async: false,
                    success: function (response) {
                        layui.each(response.data, function (index, item) {
                            lis.push('<li>' +
                                '<div style="height: 175px" class="layui-row">'+
                                    '<div style="height:100% margin: 0 auto; text-align: center" class="layui-col-xs8 ">' +
                                        '<img src="'+item.hphoto+'" width="90%" height="100%">'+
                                    '</div>'+
                                    '<div style="float:right; height: 100%" class="layui-col-xs4">'+
                                        '<div style=" height:33%">'+
                                            '<img src="img/必看.png" width="49px" height="16px">'+'<br>'+'<br>'+
                                            '<a href="/Car/getServlet?id='+item.hid+'">' +
                                            '<span style="font-size:15px">'+item.haddress+'</span>'+
                                                "——"+
                                            '<span>'+item.hid+'</span>'+
                                        '</div>'+
                                        '<div style="height: 65%">'+
                                            '<div style="height: 33%">' +
                                                '<span style="font-size:10px">面积:</span>'+
                                                '<span>'+item.harea+"m²"+'</span>'+
                                            '</div>'+
                                            '</br>'+
                                            '<div style="height: 32%">' +
                                                '<span style="font-size:10px ">价格:</span>'+
                                                '<span style="color: red">'+item.hmoney+"万"+'</span>'+
                                            '</div>'+
                                            '<div>' +
                                                '<span>预约量:</span>'+
                                                '<span>'+item.reservations+'</span>'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>'+
                                '<hr class="layui-bg-black">'+
                                '</li>');
                            //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                            //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多

                        });
                        next(lis.join(''), page < response.totalCount); //假设总页数为 10
                    }
                });
            }
        });
        flag = false;
    }



});