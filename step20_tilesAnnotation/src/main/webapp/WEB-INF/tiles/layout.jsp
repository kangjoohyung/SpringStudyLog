<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
header{width:800px;height:100px;background-color:yellow}
section{width:800px; height:400px; background-color:pink}
footer{width:800px; height:150px; background-color:skyblue}
</style>
</head>
<body>
<header>
	<tiles:insertAttribute name="header"/>
</header>
<section>
	<tiles:insertAttribute name="content"/>
</section>
<footer>
	<tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>