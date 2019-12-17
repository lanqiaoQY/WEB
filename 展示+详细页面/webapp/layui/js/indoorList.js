var vue = new Vue({
    el:'#vueTable',
    data:{
        products:null,
        img:null
    }
});
f();
function f(){
    // alert("我进来了")
    $.ajax('/Car/indoor', {
        type: 'POST',
        url: '/Car/indoor',
        dataType: 'json',
        //data:{"mobile":mobile},
        async: false,//禁用异步，变成同步
        success: function (response) {
                vue.products=response.list;
                vue.img=response.img;
                console.log("ture");
            if (result) {
            } else {
                console.log("false");
            }
        }
    });
}