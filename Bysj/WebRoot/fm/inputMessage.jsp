<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form id="inputmessage" action="fm/inputmessage" method="post">
    	<table>
    		<tbody>
    			<tr>
    				<td><label >标题：</label></td>
    				<td><input name="title" class="form-control" required="required" id="title"
    	type="text" value=""><p></td>
    			</tr>
				<tr>
					<td><label>内容：</label></td>
					<td><input name="messcontent" class="form-control" required="required" id="messcontent"
    	type="text" value=""><p></td>
    			</tr>   	    	    			
    		</tbody>
    	</table>

    	<input name="author" class="form-control" required="required" id="author"
    	type="hidden" value="${username }"><p>

    	<input class="btn btn-w-m btn-success" type="button" 
    	value="提交" onclick="inputmessage()">   
   
</form>