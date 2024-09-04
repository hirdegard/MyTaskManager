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
<h1>タスクを更新する</h1>
<form action="UpdateTaskServlet" method="post">
	<p>タスク名:
		<input type="text" name="name" value="${oldTask.name() }">
	</p>
	<p>期限:
		<input type="datetime-local" name="deadline" 
 value="<fmt:formatDate value='${oldTask.deadline()}' pattern='yyyy-MM-dd\'T\'HH:mm' />">
	</p>
	<p>説明:
		<textarea name="description" id="" cols="30" rows="10" >
			${oldTask.description() }
		</textarea>
	</p>
	<p>
		<input type="hidden" name="id" value="${oldTask.id() }">
	</p>
	<input type="submit" value="更新を確定する">
</form>
</body>
</html>