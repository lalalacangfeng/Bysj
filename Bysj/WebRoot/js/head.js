/**
 *
 */
function checkEmail() {
    var email = document.getElementById("email").value;
    console.log(email);
    var emailstatus = document.getElementById("emailstatus");
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!reg.test(email)) {
        emailstatus.innerHTML = "请输入正确的邮箱";
        //显示
        document.getElementById("regist").style.display = "none";
    } else {
        $.ajax({
            type : "post",
            url : "fm/checkEmail",
            dataType : "json",
            data : {
                "email" : email
            },
            success : function(result) {
                if (result.ifExist == 2) {
                    emailstatus.innerHTML = "邮箱已被使用";
                } else if (result.ifExist == 1) {
                    emailstatus.innerHTML = "邮箱可以使用";
                }
            }
        });
        //status.innerHTML = "邮箱可使用";//显示
    }
}

function checkName() {
    var uname = document.getElementById("uname").value;
    console.log(uname);
    var userstatus = document.getElementById("userstatus");
    if (uname != null && uname.length>6 && uname.length<15) {
        $.ajax({
            type : "post",
            url : "fm/checkName",
            dataType : "json",
            data : {
                "uname" : uname
            },
            success : function(result) {
                if (result.ifExist == 2) {
                    userstatus.innerHTML = "用户名已被使用";
                } else if (result.ifExist == 1) {
                    userstatus.innerHTML = "用户名可以使用";
                }
            }
        });
    }else{
        userstatus.innerHTML = "用户名不符合要求";
    }
}

function checkPass() {
    var pwd1 = document.getElementById("passwd1").value;
    var pwd2 = document.getElementById("secpasswd").value;
    var passwdstatus = document.getElementById("passwdstatus");
    if(pwd1.length<6 && pwd1.length>50){
        passwdstatus.innerHTML = "密码长度太短or过长";
        
    }else{

        if (pwd2 !== pwd1) {
            passwdstatus.innerHTML = "两次密码输入不一致";
        } else {
            passwdstatus.innerHTML = "";
        }

        
    }
}

function checkregister(){
     var uname = document.getElementById("uname").value;
     var pwd1 = document.getElementById("passwd1").value;
     var email = document.getElementById("email").value;
     if(uname=="" && passwd=="" && email==""){
         return false;
     }else{
         $.ajax({
            type : "post",
            url : "fm/register",
            dataType : "json",
            data : {
                "uname" : uname,"passwd":pwd1,"email":email
            },
            success : function(result) {
                if (result.ifExist == 2) {
                    alert("注册失败，请检查！");
                } else if (result.ifExist == 1) {
                    alert("注册成功！");
                    parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
                }
            }
        });
     }
}

function checklogin(){
    var uname = document.getElementById("loginuname").value;
    var passwd = document.getElementById("passwd").value;
    if(uname=="" && uname.length<6 && uname.length>15){
        alert("请输入用户名or用户名请符合要求！");
    return false;
    }
    if(passwd==""  && passwd.length<6 && passwd.length>50){
        alert("请输入密码or密码太短或太长！");
    return false;
    }
    if (uname != null) {
        $.ajax({
            type : "post",
            url : "fm/login",
            dataType : "json",
            data : {
                "uname" : uname,"passwd":passwd
            },
            success : function(result) {
                if (result.ifExist == 2) {
                    alert("登录失败，密码错误！");
                } else if (result.ifExist == 1) {
                    alert("登录成功！");
                    parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
                }
            }
        });
    }
}
