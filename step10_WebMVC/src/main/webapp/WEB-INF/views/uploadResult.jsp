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
<h3>업로드 결과</h3><br>
<h5>
작성자 : ${name }<br>
경로 : ${saveDir }<br>
첨부파일이름 : ${originalFileName }<br>
파일사이즈 : ${fileSize }<br>
</h5>

<hr>
<h3> 업로드 결과 - UploadDTO인 경우</h3><br>

<h5>
작성자 : ${uploadDTO.name }<br>
첨부파일이름 : ${uploadDTO.fileName } = ${uploadDTO.file.originalFilename }<br>
파일사이즈 : ${uploadDTO.fileSize } = ${uploadDTO.file.size}<br>
</h5>
</body>
</html>