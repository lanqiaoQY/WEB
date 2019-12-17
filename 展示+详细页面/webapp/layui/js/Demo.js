var vue = new Vue({
    el:'#vueTable',
    data:{
        products:null
    }
});

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

var totalCount = 0;
var currPage = 1;//首次加载，当前页是1
var pageSize = 8;

getData(currPage,pageSize);

function getData(currPage , pageSize) {
    var params = {
        currPage:currPage,
        pageSize:pageSize
    };
        $.ajax('/Car/list.do',{
        type: 'POST',
        url: '/Car/list.do',
        dataType: 'json',
        data: params,
        async: false,
        success: function (response) {
            vue.products = response.data;
            totalCount = response.totalCount;
        }
    });
}



$.get('/Car/list.do?currPage='+page+'&pageSize='+pageSize, function (response) {
    //假设你的列表返回在data集合中
    layui.each(response.data, function (index, item) {
        lis.push('<li>' + item.pname + '</li>');
    });

    //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
    //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
    next(lis.join(''), page < 10);
});