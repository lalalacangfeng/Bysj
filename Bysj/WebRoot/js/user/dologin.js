/**
 * 用户验证用户登录
 */

function dologin () {
  //alert("登录测试");
  
  var username = $("#username").val();//用户名
  var password = $("#password").val();//密码
  
  //var param = "/dologin?action=dologin&username="+username+"&password="+password;
  
  //判断文本框是否为空
  if(username==""){
      alert("请输入用户名！")
      return false;
  }
  else if(password==""){
      alert("请输入密码！")
      return false;
  }
  else{
      //alert("ajax测试"+username);
      $.ajax({
          type:"post",
          url:"/login.jsp",
          //dataType:"text",
          timeout:15000,
          success:function(data){
              console.log(data);
              if(data==false){
                  alert("用户名或者密码输入错误!");
                  window.location.href = 'login.jsp';
              }
              if (data==true) {
                  alert("登录成功");
                  window.location.href = 'index.jsp';
              };
          },
          error:function(XMLHttpRequest,textStatus){
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus); 
          }
      });
      //alert("ajax测试"+password)
  }
  
  
  
}
