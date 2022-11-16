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
가입결과<br> 
아이디 : ${member.id }<br>
이름 : ${member.name }<br>
나이 : ${member.age }<br>
주소 : ${member.addr}<br>

<hr>
@ModelAttribute("m") 사용하기<br>
가입결과 <br>
아이디 : ${m.id }<br>
이름 : ${m.name }<br>
나이 : ${m.age }<br>
주소 : ${m.addr}<br>

<hr>
정보 : ${info }<p>
메뉴 : ${menus }<p>


</body>
</html>