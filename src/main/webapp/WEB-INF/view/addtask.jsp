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
<h1>タスクの追加</h1>
<form method="post" action="AddTaskServlet">
	<p>
		<input type="text" name="name" placeholder = "タイトル" required>
	</p>
	<p>
		<input type="datetime-local" name="deadline" placeholder = "日時">
	</p>
	<p>
		<textarea name="description" id="" cols="30" rows="10" 
			placeholder="詳細"></textarea>
	</p>
	<p>
		<input type="submit" value="追加する">
	</p>
</form>
</body>
</html>