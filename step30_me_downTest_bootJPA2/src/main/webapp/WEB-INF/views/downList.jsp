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
<h3>downList(파일목록) 입니다</h3>

<ul>
<c:forEach items="${fileNames }" var="f">
	<li><a href="${pageContext.request.contextPath }/down?fileName=${f}">${f }</a></li>
</c:forEach>
</ul>
</body>
</html>