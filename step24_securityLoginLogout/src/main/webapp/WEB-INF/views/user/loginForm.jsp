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
user/loginForm.jsp<br>
<c:if test="${param.err!=null }">
	Error message : 
	<b style="color:red">${SPRING_SECURITY_LAST_EXCEPTION.message }</b>
</c:if>

<form action="${pageContext.request.contextPath }/loginCheck" method="post">
<input type="text" name="id">로그인 아이디 <br>
<input type="password" name="pwd">패스워드 <br>
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
<input type="submit" value="로그인">
<input type="reset" value="취소">
</form>
</body>
</html>