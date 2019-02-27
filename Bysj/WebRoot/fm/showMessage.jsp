<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>标题</th>
								<th>内容</th>
								<th>作者</th>
								<th>发表时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${messages }" var="message">
								<tr class="gradeX">
									<td><c:out value="${message.title}" /></td>
									<td><c:out value="${message.content}" /></td>
									<td><c:out value="${message.author}" /></td>
									<td><fmt:formatDate value="${message.releasetime}"
											pattern="yyyy年MM月dd日 HH时mm分ss秒" /></td>
									<c:if test="${message.author == username}">
										<td>
										<a href="fm/user?action=delmessage&mid=${message.mid }" onClick="return confirm('确定删除?')"><i class="fa fa-close"> 删除</a></td>
									</c:if>
									<c:if test="${message.author != username}">
										<td></td>
									</c:if>

								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th>标题</th>
								<th>内容</th>
								<th>作者</th>
								<th>发表时间</th>
								<th>操作</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>



