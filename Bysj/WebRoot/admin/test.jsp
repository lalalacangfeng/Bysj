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

  </head>
  
  <body>
	<div class="formtitle upLoad">附件上传</div>
	  <div class="chooseFile" style="height: auto;">
	      <input type="button" id="uploadbtn" value="选择文件..." />
	      <input type="button" id="uploadfile"  value="上传文件" onclick="uploadFile()" />
	      <input type="file" id="uploadpicker" accept="" style="display: ;" multiple="multiple" />
	      <div>
	          <ul id="checkfileList"></ul>
	      </div>
	 </div>
  </body>
  
  <script src="js/jquery.min.js?v=2.1.4"></script>
  <script src="js/plugins/jquery-easyui-1.6.10/jquery.easyui.min.js"></script>
  
  <script type="text/javascript">
var selectedFileList = [];//已选文件列表
var paramTaskId = "";//请求参数
var succsessCount = 0;//上传成功文件个数
var errorCount = 0;//上传失败文件个数
$(function () {
    $("#uploadbtn").bind("click", function (e) {
        $("#uploadpicker").click();
    });
    $("#uploadpicker").bind("change", function () {//绑定文件选择事件
   		 debugger;
        var files = $("#uploadpicker").prop("files");
        $.each(files, function (index, item) {
            var choiseFile = $("#checkfileList>li");
            if (choiseFile.length > 0) {//文件去重
                var count = 0;
                $.each(choiseFile, function (index1, item1) {
                    if (item.name == item1.innerText) {
                        count++;
                    }
                });
                if (count == 0) {
                    if (checkFileLength(item.size)) {
                        $("#checkfileList").append("<li class=\"fileinfo\">" + item.name + "<a style=\"display: none; color: red; margin-left: 10px;\" href=\"javascript:void(0)\" onclick=\"RemoveFile(this)\">删除</a>" + "</li>");
                        selectedFileList.push(item);
                        AddMoushover();
                    }
                }
            } else {
                if (checkFileLength(item.size)) {
                    $("#checkfileList").append("<li class=\"fileinfo\">" + item.name + "<a style=\"display: none; color: red; margin-left: 10px;\" href=\"javascript:void(0)\" onclick=\"RemoveFile(this)\">删除</a>" + "</li>");
                    selectedFileList.push(item);
                    AddMoushover();
                }
            }
        });
    });
    AddMoushover();
});
//判断文件是否超过限制大小
function checkFileLength(fileLen) {
    if (fileLen > 4194304) {
        $.messager.alert("提示", "上传文件大小不能超过4M","error");
        return false;
    }
    return true;
}
 //上传文件
function uploadFile() {
    if (selectedFileList.length>0) {
    $.messager.progress({
        title: "提示",
        msg: "文件上传中..."
    });
    $.each(selectedFileList, function (index, item) {
        debugger;
        var dataform = new FormData();
        dataform.append("file", item);
        var xmlhttp = null;
        if (window.XMLHttpRequest) {// code for all new browsers
            xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) {// code for IE5 and IE6
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (xmlhttp != null) {
            xmlhttp.open("POST", "admin/media?action=pict" + paramTaskId, true);
            xmlhttp.send(dataform);
            xmlhttp.onreadystatechange = callbackMethods;
        } else {
            alert("你的浏览器版本不兼容，请使用更高版本的浏览器");
        }          //请求回调函数
        function callbackMethods() {
            debugger;
            if (xmlhttp.readyState == 4) {
                if (xmlhttp.status == 200) {
                    succsessCount++;
                    if ((succsessCount + errorCount) == selectedFileList.length) {
                        if (errorCount > 0) {
                            $.messager.alert("提示", "上传文件完成," + errorCount + "个文件由于过大上传失败。", "error");
                        }
                        submitSuccess();
                    }
                } else {
                    if (xmlhttp.status==500) {
                        errorCount++;
                        if ((succsessCount + errorCount) == selectedFileList.length) {
                            if (errorCount>0) {
                                $.messager.alert("提示", "上传文件完成,"+errorCount+"个文件由于过大上传失败。", "error");
                            }
                            submitSuccess();
                        }
                    }
                }
            }
        }
    });
 
    } else {
        submitSuccess();
    }
}
 //移除已选择的文件
function RemoveFile(obj) {
    $.messager.confirm("提示", "确认删除当前文件?", function(e) {
        if (e) {
            var index = $("#checkfileList>li").index($(obj).parent());
            selectedFileList.splice(index, 1);
            $(obj).parent().remove();
        }
    });
}
   //添加鼠标经过事件，鼠标划过时显示“删除”
function AddMoushover() {
    $(".fileinfo").hover(function () {
        $(this).find("a").last().css("display", "");
    }, function () {
        $(this).find("a").last().css("display", "none");
    });
}
  </script>
</html>
