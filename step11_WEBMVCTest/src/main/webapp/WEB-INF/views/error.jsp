<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>오류 : ${errMessage}</h1>
<a href="${pageContext.request.contextPath}/index.kosta">이동하기</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:history.back()">뒤로가기</a>

</body>
</html>