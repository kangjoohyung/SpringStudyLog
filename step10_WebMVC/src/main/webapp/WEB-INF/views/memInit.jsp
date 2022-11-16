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
Member정보 출력하기
<c:forEach items="${memberList }" var="m">
${m.id }<br>
${m.age }<br>
${m.state }<br>
${m.addr }<br>
${m.name }<br>
</c:forEach>
</body>
</html>