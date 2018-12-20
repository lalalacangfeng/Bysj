<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改栏目</title>
    
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
	<link href="css/style.css?v=4.1.0" rel="stylesheet">
	
	<style>
		.div-l{ float:left;width:49% } 
		.div-r{ float:right;width:49% } 
		.div-c{ float:left;width:98% }
	</style>
	
  </head>
  
  <body>
    <form id="columns" action="admin/column" method="post">
    	<div class="wrapper wrapper-content animated fadeInRight">
			<div class="div-l">
	        <label style="display:none" class="col-sm-3 control-label">栏目编号：
			<c:choose>
				<c:when test="${column.pid == 1 }">
					<input type="hidden" name="id" value="${column.kid }">
				</c:when>
				<c:when test="${column.pid == 2 }">
					<input type="hidden" name="id" value="${column.cid }">
				</c:when>
			</c:choose>
	        </label>

			
			<label class="col-sm-3 control-label">栏目名称：</label>
			<c:choose>
				<c:when test="${column.pid == 1 }">
			            <input type="text" name="inputname" class="form-control"
			            value="${column.kindname }" placeholder="请输入文本">
				</c:when>
				<c:when test="${column.pid == 2 }">
			            <input type="text" name="inputname" class="form-control"
			            value="${column.columnname }" placeholder="请输入文本">				
				</c:when>
			</c:choose>
	        <br>
					        
	        <label class="col-sm-3 control-label">所属类别：</label>

	            <select class="form-control" name="optionsRadios"  id="optionsRadios"
	            onchange="gradeChange()" >
	            <c:choose>
	            	<c:when test="${column.pid ==1 }">
	            		<option grade="1" value="1">一级栏目</option>
	            	</c:when>
	            	<c:when test="${column.pid ==2 }">
	            		<option grade="2" value="2">二级栏目</option>
	            	</c:when>
	            </c:choose>
	            	
	            </select>
	            
		        <c:choose>
	            	<c:when test="${column.pid ==1 }">
	            	<div id="cname" style="display:none">
	            	&nbsp;<br>
				        <label class="col-sm-3 control-label" >所属栏目列表：</label>
				            <select class="form-control" name="columnname">
				            	<option name="${column.kid }" value="${column.kid }">${column.kindname }</option>
				            	<c:forEach items="${columns }" var="column">
				            	<c:if test="${column.pid == 1 }">
			            			<option name="${column.kid }" value="${column.kid }">${column.kindname }</option>
		            			</c:if>
				            	</c:forEach>
				            </select>
	            	</div>
	            	</c:when>
	            	<c:when test="${column.pid ==2 }">
					<div id="cname">
				        &nbsp;<br>
				        <label class="col-sm-3 control-label" >所属栏目列表：</label>
				            <select class="form-control" name="columnname">
				            	<option name="${column.kid }" value="${column.kid }">${column.kindname }</option>
				            	<c:forEach items="${columns }" var="column">
				            	<c:if test="${column.pid == 1 }">
			            			<option name="${column.kid }" value="${column.kid }">${column.kindname }</option>
		            			</c:if>
				            	</c:forEach>
				            </select>
			        </div>
	            	</c:when>
	            </c:choose>
		       
		       	&nbsp;<br> 
		        
				<input type="submit" name="action" class="btn btn-w-m btn-success" value="修改" >
			</div>
		</div>
    </form>
    <script type="text/javascript">
        var objS = document.getElementById("optionsRadios");
    	var cname = document.getElementById('cname');

       function gradeChange(){
	        var objS = document.getElementById("optionsRadios");
	        var grade = objS.options[objS.selectedIndex].getAttribute('grade');;
	        alert(grade);
	        if(grade == 1){
	        	cname.style.display = "none";//隐藏
	        }else if(grade == 2){
	        	cname.style.display = "";//显示
	        }
       		
       		/*
			var parent = document.getElementById('cname');
			//添加 label
　　　　		var label = document.createElement("label");
			label.setAttribute("class", "col-sm-3 control-label");
			label.innerHTML = "所属栏目列表：";
			parent.appendChild(label);
       		*/
       }

	</script>  
  </body>
</html>
