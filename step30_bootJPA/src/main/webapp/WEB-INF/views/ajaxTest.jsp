<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
$(function(){
	alert("ajax");
	
	$.ajax({ //타입은 더 있지만 이정도 사용(배운것에 대해서는)
		  url : "${pageContext.request.contextPath}/ajax", //서버요청주소->RequestMapping으로 연결-AjaxController
		  type : "post", //요청방식(get, post, put, delete, patch)
		  dataType : "json", //서버가 응답(보내온)한 데이터타입(text|html|xml|json)->4가지만 존재
		  data : "name=장희정", //서버에게 보낼 parameter정보 (컨트롤러에서 인수)
		  success : function(result){//성공했을때 : 보통 콜백함수
			  $.each(result, function(index, item){
				  alert(item)
			  })
		  }, 
		  error : function(err){//에러 : 보통 콜백함수
				alert(err+": 에러 발생!");	  
		  } 
	  })//ajax끝
})
</script>
</head>
<body>
<h2>Ajax Test하기</h2>

</body>
</html>