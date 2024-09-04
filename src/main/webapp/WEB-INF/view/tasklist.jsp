<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>タスク一覧</h1>
<p>
	<a href="AddTaskServlet">タスクの追加</a>
</p>
<table border="1">
	<tr>
		
		<th>タスク名</th>
		<th>締め切り</th>
		<th>説明</th>
	</tr>
	<c:forEach items = "${taskList }" var = "task">
		<tr>
			<td>
				<c:out value="${task.name() }" />
			</td>
			<td>
				<c:out value="${task.deadline() }" />
			</td>
			<td>
				<c:out value="${task.description() }" />
			</td>
			<td>
				<form action="DeleteTaskServlet" method="post">
					<input type="hidden" name="id" value="${task.id() }">
					<input type="submit" value="削除">
				</form>
			</td>
			<td>
				<form action="UpdateTaskServlet" method="get">
					<input type="hidden" name="id" value="${task.id() }">
					<input type="submit" value="編集">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>