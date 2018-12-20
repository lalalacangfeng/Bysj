/**
 * 用户验证用户登录
 */

function dologin () {
  alert("登录测试");
  
  var username = $("#username").val();//用户名
  var password = $("#password").val();//密码
  var form = new FormData(document.getElementById("dologin"));
  var param = "login.jsp?action=login&username="+username+"&password="+password;
  //判断文本框是否为空
  if(username==""){
      alert("请输入用户名！");
      return false;
  }
  else if(password==""){
      alert("请输入密码！");
      return false;
  }
  else{
      alert("ajax测试"+username);
      
      $.ajax({
                url:param,//地址不能写错---苦苦
                type:"post",
                //dataType:"text",//暂时使用text，待改为json  data : form,
                async : false,
                cache:false,
                processData:false,
                // contentType: false,
                contentType: "application/json; charset=utf-8",   
                success:function(data,xhr){
                    console.log(xhr.responseText);
                    //console.log(data);
                    // if(data==1){
                        // alert(1);
                    // }
                    // else if (data==0){
                        // alert(2);
                        // window.location.href ="index.jsp";
                    // }else{
                        // alert(3);
                    // }
                    //data = $.parseJSON(data);
                    // if(data.success){
                        // alert("登录成功");
                    // }
                    // else{
                        // alert("登陆失败");
                    // }
                    //mycallback(data,status);//回调函数);
                    //parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
                },  
                error:function(XMLHttpRequest, textStatus, errorThrown){  
                    //alert(XMLHttpRequest.status);
                    //alert(XMLHttpRequest.readyState);
                    //alert(textStatus); 
                    alert("网络错误，请重试！！");  
                }  
                
            });
      
      alert("ajax测试"+password);
  }
}
