<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>param/a.do 요청결과</h2>
메세지 : ${message }<p>

<fieldset>
	<legend>취미</legend>
		<c:forEach items="${hobbys }" var="h" varStatus="state">
			<input type="checkbox" name="hobby" value="${state.count }">${h }
		</c:forEach>
</fieldset>
</body>
</html>