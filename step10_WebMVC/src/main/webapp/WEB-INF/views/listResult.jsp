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
<h1>등록 결과</h1>
\${memberListDTO.list}=${memberListDTO.list }<p>
<table>
<tr>
<td>이름</td><td>나이</td><td>주소</td>
</tr>
<c:forEach items="${memberListDTO.list}" var="l" varStatus="state">
<tr>
<td>${l.name }</td><td>${l.age }</td><td>${l.addr }</td>
<c:if test="${l.state}=='false'">
	<c:set var="doneLoop" value="true"/>
</c:if>
</tr>
</c:forEach>
</table>

</body>
</html>