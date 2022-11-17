<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- view에서 사용할 library니 webapp에 넣기 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
  $(function(){
	  //alert("OK~"); //확인용
	  $("#loadBtn").click(function(){
		  //ajax->$().load(), $.getJSON(), $.get(), $.post(), $.ajax(), $.ajaxForm()
		 
		  $("#display").load("load"); // 인수의 서버 요청 주소 ->AjaxController에 /load로 연결
		  //이것만해도 비동기식 ajax사용 되는듯?
	  });
	  
	  $("#ajaxBtn").click(function(){
		  $.ajax({ //타입은 더 있지만 이정도 사용(배운것에 대해서는)
			  url : "${pageContext.request.contextPath}/ajax", //서버요청주소->RequestMapping으로 연결-AjaxController
			  type : "post", //요청방식(get, post, put, delete, patch)
			  dataType : "text", //서버가 응답(보내온)한 데이터타입(text|html|xml|json)->4가지만 존재
			  data : "name=장희정", //서버에게 보낼 parameter정보 (컨트롤러에서 인수)
			  success : function(result){//성공했을때 : 보통 콜백함수
				  $("#display").html("<h3>"+result+"</h3>");
			  }, 
			  error : function(){//에러 : 보통 콜백함수
					alert(err+": 에러 발생!");	  
			  } 
		  })//ajax끝
	  })//#ajaxBtn.click 끝
	  //////////////////////////
	  $("#jsonBtn").click(function(){
		  $.ajax({ //타입은 더 있지만 이정도 사용(배운것에 대해서는)
			  url : "${pageContext.request.contextPath}/json", //서버요청주소->RequestMapping으로 연결-AjaxController
			  type : "post", //요청방식(get, post, put, delete, patch)
			  dataType : "json",
				  //"text", //서버가 응답(보내온)한 데이터타입(text|html|xml|json)->4가지만 존재
			  //data : "name=장희정", //서버에게 보낼 parameter정보 (컨트롤러에서 인수)
			  success : function(result){//성공했을때 : 보통 콜백함수
				  //alert(result) //dataType text로 사용후 확인해보기 
				  //결과를 input type="checkbox"로 만들어서 display에 출력해보기(반복문사용)
				let str="";  
			  	$.each(result, function(index, item){
			  		str+="<input type='checkbox' name='menu' value='"+index+"'>"+item
			  	});
			  	$("#display").html(str);
			  	
			  }, 
			  error : function(){//에러 : 보통 콜백함수
					alert(err+": 에러 발생!");	  
			  } 
		  })//ajax끝
	  })//#jsonBtn끝
	  /////////////////////////////////////
	  $("#dtoBtn").click(function(){
		  $.ajax({ //타입은 더 있지만 이정도 사용(배운것에 대해서는)
			  url : "${pageContext.request.contextPath}/memberDTO", //서버요청주소->RequestMapping으로 연결-AjaxController
			  type : "post", //요청방식(get, post, put, delete, patch)
			  dataType : "json",
				  //"text", //서버가 응답(보내온)한 데이터타입(text|html|xml|json)->4가지만 존재
			  //data : "name=장희정", //서버에게 보낼 parameter정보 (컨트롤러에서 인수)
			  success : function(result){//성공했을때 : 보통 콜백함수
				  //alert(result) //dataType text로 사용후 확인해보기
				  alert(result.id+","+result.name) //DTO에서 특정값 꺼내오기(json-맵형식)
			  }, 
			  error : function(){//에러 : 보통 콜백함수
					alert(err+": 에러 발생!");	  
			  } 
		  })//ajax끝
	  })//#dtoBtn 끝
	  ///////////////////////
	  $("#listBtn").click(function(){
		  $.ajax({ //타입은 더 있지만 이정도 사용(배운것에 대해서는)
			  url : "${pageContext.request.contextPath}/list", //서버요청주소->RequestMapping으로 연결-AjaxController
			  type : "post", //요청방식(get, post, put, delete, patch)
			  dataType : "json",
			  //"text", //서버가 응답(보내온)한 데이터타입(text|html|xml|json)->4가지만 존재
			  //data : "name=장희정", //서버에게 보낼 parameter정보 (컨트롤러에서 인수)
			  success : function(result){//성공했을때 : 보통 콜백함수
				  //alert(result) //dataType text로 사용후 확인해보기
				  //alert(result.id+","+result.name) //DTO에서 특정값 꺼내오기(json-맵형식)
			  
				let str="";  
			  	$.each(result, function(index, mem){
			  		str+=mem.id+"|"+mem.name+"|"+mem.age+"|"+mem.addr+"<br>";
			  	});
			  	$("#display").html(str);
				  	
			  }, 
			  error : function(){//에러 : 보통 콜백함수
					alert(err+": 에러 발생!");	  
			  } 
		  })//ajax끝
	  })//#listBtn끝
	  ///////////////////////////////
	  $("#mapBtn").click(function(){
		  $.ajax({ //타입은 더 있지만 이정도 사용(배운것에 대해서는)
			  url : "${pageContext.request.contextPath}/map", //서버요청주소->RequestMapping으로 연결-AjaxController
			  type : "post", //요청방식(get, post, put, delete, patch)
			  dataType : "json",
			  //"text", //서버가 응답(보내온)한 데이터타입(text|html|xml|json)->4가지만 존재
			  //data : "name=장희정", //서버에게 보낼 parameter정보 (컨트롤러에서 인수)
			  success : function(result){//성공했을때 : 보통 콜백함수
				  //alert(result); //dataType text로 사용후 확인해보기
				  //alert(result.id+","+result.name) //DTO에서 특정값 꺼내오기(json-맵형식)
// 				  alert(result.message);
// 				  alert(result.pageNo);
// 				  alert(result.dto+"|"+result.dto.id+"|"+result.dto.name);
// 				  alert(result.memberList);
			  
				let str="";  
			  	$.each(result.memberList, function(index, mem){
			  		str+=mem.id+"|"+mem.name+"|"+mem.age+"|"+mem.addr+"<br>";
			  	});
			  	$("#display").html(str);
				  	
			  }, 
			  error : function(){//에러 : 보통 콜백함수
					alert(err+": 에러 발생!");	  
			  } 
		  })//ajax끝
	  })//#mapBtn끝
	  
  })
  
</script>
</head>
<body>

<input type="button" value="load함수" id="loadBtn">
<input type="button" value="ajax함수" id="ajaxBtn">
<input type="button" value="json결과" id="jsonBtn">
<br>
<input type="button" value="DTO결과" id="dtoBtn">
<input type="button" value="List결과" id="listBtn">
<input type="button" value="Map결과" id="mapBtn">
<hr>
<div id="display"></div>



</body>
</html>



