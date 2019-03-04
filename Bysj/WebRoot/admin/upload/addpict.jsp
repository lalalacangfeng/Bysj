<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("path:"+path+"      basePath:"+basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="shortcut icon" href="favicon.ico"> 
    <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">

	<link href="css/admin/style.css?v=4.1.0" rel="stylesheet">
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
	<form id="uploadmedia" name="uploadmedia" action="admin/media" 
    encType="multipart/form-data" method="post" onsubmit="return false">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="ibox float-e-margins">
				<ul class="forminfo">

					<li>
						<label class="col-sm-3 control-label">名称：</label>
						<input type="text" id="name" name="name" required="required" 
					    class="form-control" value="${media.name }" placeholder="请输入标题">
					    <br>
					</li>
					<li>
						<label class="col-sm-3 control-label">描述：</label>
						<input type="text" id="descript" name="descript" required="required" 
					    class="form-control" value="${media.descript }" placeholder="请输入描述">
					    <br>
					</li>
				<div id="picture">
					<li>
						<label class="col-sm-3 control-label">上传资源&nbsp;&nbsp;</label>	
			      		<input type="file" class="form-control" id="choose-file" multiple>
					    <br>
					</li>
					<ul class="file-list">
    				</ul>
				</div>

					<li>
						<label>&nbsp;</label>
						<input id="upload" name="upload"
						type="button" class="btn" value="提交"/>

						<br>
					</li>
					
               </ul>
        	</div>
    	</div>
   </form>
   
    <link href="js/plugins/jquery-easyui-1.6.10/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="js/plugins/jquery-easyui-1.6.10/themes/icon.css" rel="stylesheet" type="text/css" />
   
   
   	<!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
  <script src="js/plugins/jquery-easyui-1.6.10/jquery.easyui.min.js"></script>
    <script src="js/media/addpict.js"></script>    
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
 var curFile;

 var flag;

 $file.on('change', function () {
 	//原生的文件对象，相当于$file.get(0).files[0];
 	curFile = this.files;
 	//将FileList对象变成数组
 	fileList = fileList.concat(Array.from(curFile));
 	for (var i = 0, len = curFile.length; i < len; i++) {
 		reviewFile(curFile[i])
 	}

 })
 function reviewFile(file) {
 	//实例化fileReader,
 	let fd = new FileReader();
 	//获取当前选择文件的类型
 	let fileType = file.type;
 	//调它的readAsDataURL并把原生File对象传给它，
 	fd.readAsDataURL(file); //base64
 	//监听它的onload事件，load完读取的结果就在它的result属性里了

 	fd.onload = function () {

 		if (/^image\/[jpeg|png|jpg|gif]/.test(fileType)) {
 			$list.append('<li class="file-item"><img src="' + this.result + '" alt=""><span class="file-name">' + file.name + '</span><span class="file-del">删除</span></li>')
 		} else {
 			$list.append('<li class="file-item"><span class="file-name">' + file.name + '</span><span class="file-del">删除</span></li>')
 		}
 	}
 }

 $(".file-list").on('click', '.file-del', function () {
 	let $parent = $(this).parent();
 	let index = $parent.index();
 	fileList.splice(index, 1);
 	$parent.remove()
 });

 $button.on('click', function () {

 	if ($("#name").val() == "") {
 		alert("文件名不能为空!");
 		return false;
 	}
 	if ($("#descript").val() == "") {
 		alert("请添加描述!");
 		return false;
 	}

 	if (fileList.length > 0) {
 		for (var i = 0, len = fileList.length; i < len; i++) {
 			let formData = new FormData();
 			formData.append('name', $("#name").val());
 			formData.append('descript', $("#descript").val());
 			formData.append('file', fileList[i]);
 			$.ajax({
 				url: 'admin/media?action=pict',
 				type: 'post',
 				data: formData,
 				dataType: "json",
 				processData: false,
 				contentType: false,

				async: false, //同步传输，并添加返回值，返回值应为已定义的全局变量 
 				success: function (result) {
 					console.log(result);
 					console.log("successflag1:"+result);
 					if(result.ifExist == 1){
                        alert("上传成功！");
                        window.location.href ="admin/media?action=update";
                    }
                    else if(result.ifExist == 2){
                        alert("上传失败！");
                    }
                    else if(result.ifExist == 3){
                        alert("上传失败！图片已经存在！");
                    }
 				},
 				error: function (XMLHttpRequest, textStatus, errorThrown) {
 					//alert(XMLHttpRequest.status);
 					//alert(XMLHttpRequest.readyState);
 					//alert(textStatus);
 					console.log("errorflag1:"+flag);
 					flag = false;
 					alert("网络错误，请重试！！");
 					console.log("errorflag2:"+flag);
 				}
 			})
 		}
	console.log("flag3:"+flag);

 	} else {
 		alert("请选择文件！")
 	}
 	if (flag) {
 			alert("准备跳转！");
		 	window.location.href ="admin/media?action=update";
 		}


 })
  </script>
  

  </body>
</html>
