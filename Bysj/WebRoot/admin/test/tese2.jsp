<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
 	<link href="js/plugins/jquery-easyui-1.6.10/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="js/plugins/jquery-easyui-1.6.10/themes/icon.css" rel="stylesheet" type="text/css" />

<style>
       .file-list{
           padding: 20px;
           background: lightblue;
           list-style-type: none;
       }
        .file-list img{
            max-width: 70px;
            vertical-align: middle;
        }
        .file-list .file-item{margin-bottom: 10px}
        .file-list .file-item .file-name{margin-left: 20px}
        .file-list .file-item .file-del{
            color: red;
            margin-left: 200px;}
    </style>

  </head>
  
  <body>
	<form >
    <div>
        <input type="file" id="choose-file" multiple>
        <a href="javascript:;" id="upload">上传</a>
    </div>
    <ul class="file-list">
    </ul>
	</form>
  </body>
  
      <script src="js/jquery.min.js?v=2.1.4"></script>
  
  <script type="text/javascript">
  var $button = $('#upload'),
         //选择文件按钮
         $file = $("#choose-file"),
         //回显的列表
         $list = $('.file-list'),
         //选择要上传的所有文件
         fileList = [],
         sendList = [];
     //当前选择上传的文件
     var curFile ;
     
     
     $file.on('change',function(){       
  //原生的文件对象，相当于$file.get(0).files[0];
         curFile = this.files;
         //将FileList对象变成数组
         fileList = fileList.concat(Array.from(curFile));
         for(var i=0,len = curFile.length;i < len;i++){
             reviewFile(curFile[i])
         }

     })
     function reviewFile(file){
         //实例化fileReader,
         let  fd = new FileReader();
         //获取当前选择文件的类型
         let fileType = file.type;
         //调它的readAsDataURL并把原生File对象传给它，
         fd.readAsDataURL(file);//base64
         //监听它的onload事件，load完读取的结果就在它的result属性里了

         fd.onload = function(){

             if(/^image\/[jpeg|png|jpg|gif]/.test(fileType)){
                 $list.append('<li class="file-item"><img src="'+this.result+'" alt=""><span class="file-name">'+file.name+'</span><span class="file-del">删除</span></li>')
             }else{
                 $list.append('<li class="file-item"><span class="file-name">'+file.name+'</span><span class="file-del">删除</span></li>')
             }
         }
     }
     
     $(".file-list").on('click','.file-del',function(){
         let $parent = $(this).parent();
         let index = $parent.index();
         fileList.splice(index,1);
         $parent.remove()
     });
     
     $button.on('click',function(){

         if(fileList.length>0){
             for(var i=0,len = fileList.length;i < len;i++){
                 let formData = new FormData();
                 formData.append('file',fileList[i]);
                 $.ajax({
                     url:'admin/media?action=pict',
                     type:'post',
                     data:formData,
                     processData:false,
                     contentType:false,
                     success:function(data,statusText,headers){
                         if(data.success){
                             var filed = data.data[0];
                             sendList.push(filed);
                         }
                     }
                 })
             }

         }else{
             alert("请选择文件！")
         }
         return false;

     })
  </script>
  
  
</html>
