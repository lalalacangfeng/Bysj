/**
 * 用户验证用户登录
 */

function dologin () {
    alert("登录测试");
  
    var username = $("#username").val();//用户名
    var password = $("#password").val();//密码
    var status = $("#status").val();//通知状态
    var param = "login.jsp?action=login&username="+username+"&password="+password;

    // if(status==""){
        // return;
    // }
    // else{
        // alert(status);
    // }
    //判断文本框是否为空
    if(username==""){
        alert("请输入用户名！");
    return false;
    }
    else if(password==""){
        alert("请输入密码！");
    return false;
    }
}
