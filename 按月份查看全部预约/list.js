var vue = new Vue({
    el:'#vueTable',
    data:{
        products:null
    }
});
var totalCount = 0;
var currPage = 1;//首次加载，当前页是1
var pageSize = 8;

f();
function f() {
//layUI分页
    layui.use('laypage',function () {
        var laypage = layui.laypage;
        laypage.render({
            elem:"pagecontainer",
            count:totalCount,
            limit:8,
            layout:['count','prev','page','next','limit','refresh','skip'],
            jump:function (obj, first) {
                //首次不执行
                if(!first){
                getData(obj.curr , obj.limit);
                 }
            }
        })
    })


    getData(currPage,pageSize);

    function getData(currPage , pageSize) {
        var params = {
            currPage:currPage,
            pageSize:pageSize
        };
        $.ajax('/zxc000_war/list',{
            type: 'POST',
            url: '/zxc000_war/list',
            dataType: 'json',
            data: params,
            async: false,
            success: function (response) {
                vue.products = response.data;
                totalCount = response.totalCount;
            }
        });
    }
}


function f1() {

    var val = document.getElementById("test1").value;
    var totalCount = 0;
    var currPage = 1;//首次加载，当前页是1
    var pageSize = 8;

//layUI分页




    getData(currPage, pageSize);

    function getData(currPage, pageSize) {
        var params = {
            currPage: currPage,
            pageSize: pageSize,
            val: val
        };
        alert("我进来了")
        $.ajax('/zxc000_war/list?key=1', {
            type: 'POST',
            url: '/zxc000_war/list?key=1',
            dataType: 'json',
            data: params,
            async: false,
            success: function (response) {
                alert(response.totalCount)
                vue.products = response.data;
                totalCount = response.totalCount;

            }
        });

    }

    layui.use('laypage',function () {
        var laypage = layui.laypage;
        laypage.render({
            elem:"pagecontainer",
            count:totalCount,
            limit:8,
            layout:['count','prev','page','next','limit','refresh','skip'],
            jump:function (obj, first) {
                //首次不执行
                if(!first){
                    getData(obj.curr , obj.limit);
                }
            }
        })
    })
}
