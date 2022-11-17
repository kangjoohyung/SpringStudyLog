<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 계좌이체</h1>
<form action="transfer" method="post">
  출금계좌 : <input type="text" name="output"/><br>
  입금계좌 : <input type="text" name="input"/><br>
  이체금액 :  <input type="text" name="transferAmount"/><br>
  <input type="submit" value="이체하기"/>
</form>




</body>
</html>