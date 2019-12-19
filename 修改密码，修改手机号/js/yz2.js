//修改密码页判断是否登陆
f();
function f(){
    var  value= sessionStorage.getItem("flg");
    if(value==null || value==undefined){
        setTimeout(function(){
            sessionStorage.setItem("flg","2");
            window.location.href="http://mz.free.idcfengye.com/c12/SessionServlet2";
        },0);
    };
    sessionStorage.clear();
}
