<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<sec:authentication var="mvo" property="principal" />

 ${mvo.address}에 사는 
 ${mvo.name}님  카페입장 환영!
 <br><br>
총 회원수 ${requestScope.count}명 
</body>
</html>