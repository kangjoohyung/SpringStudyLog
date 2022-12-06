<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <meta name="_csrf_header" th:content="${_csrf.headerName}"> --%>
<%-- <meta name="_csrf" th:content="${_csrf.token}"> --%>
<meta name="_csrf" content="${_csrf.token}"/>
<!-- 403에러 대비 -->
<title>Insert title here</title>
  <!-- jQuery -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js" ></script>
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
  <!-- 주소지API(다음주소api) -->
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function() {
	
// 	var header = $("meta[name='_csrf_header']").attr('content');
// 	var token = $("meta[name='_csrf']").attr('content');
	
		//폼양식 name에 entity기준으로 동일하게 넣기
		//폼양식 안에 히든으로 넣기 1,2,3
		//1) security: <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		//2) productList:<input type="hidden" name="${productList}" value="${productList}">
		//3) orderdetailsList:<input type="hidden" name="${orderdetailsList}" value="${orderdetailsList}">
		
		// var csrfToken = $("meta[name='_csrf']").attr("content");
	 /* $.ajaxPrefilter(function(options, originalOptions, jqXHR){
	    if (options['type'].toLowerCase() === "post") {
	        jqXHR.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	    }
	  }); *///403에러 대비
		
// 			alert("시작"); //호출됨
//             function checkout(){
		$("#checkOutBtn").on("click", function() {
			//IMP객체 생성, 결제준비
			var IMP = window.IMP;
			IMP.init("imp75802403"); //가맹점 식별코드
			//결제 준비하기 끝
			
			//결제하기 버튼 누르면 주문 실행
			//리턴값 주문정보orders, 총합계=amount 결제할 때 사용
// 			alert("버튼클릭");//호출됨
			$.ajax({
				url: "${pageContext.request.contextPath}/orders/checkout", //${pageContext.request.contextPath}
				type: "post",
// 				beforeSend: function(xhr){
// 			        xhr.setRequestHeader(header, token);
// 			    },
				//contentType : "application/json",//컨트롤러로 보낼떄
 				dataType: "json", //단건결제시 text //"json", //여러개 구매시 json?확인해볼것
				//dataType: "text", //리턴값 타입
// 				data: JSON.stringify({$("#ordersForm").serialize()}),
				data: $("#ordersForm").serialize(), 
				success: function(result){
// 					alert("db성공, amount="+result);//출력확인
// 					alert(result.orders.ordersNo);
// 					alert(result.amount);
// 					alert(result.orders.ordersReceiverName);
// 					//IMP객체 생성, 결제준비
// 					var IMP = window.IMP;
// 					IMP.init("imp75802403"); //가맹점 식별코드
// 					//결제 준비하기 끝
					
					
					//회원 정보대신 주문 정보 송출
// 					//없을때는 회원정보로 전송(email)->주석처리
// 					IMP.request_pay(param, callback) 결제창 호출
					IMP.request_pay({
						pg : "kakaopay", //"html5_inicis" //$("#paymentMethod").val()->선택지두고 페이팔도 구현할 수 있음
						pay_method : "card",
						merchant_uid : result.orders.ordersNo ,//"${orders.ordersNo}" ,//result.orders.ordersNo , 
						name : "BLINK_TOWN_GOODS",
						amount : result.amount ,//"${amount}", //result.amount , 
// 						buyer_email : "kangjoohyung89@gmail.com", //"${orders.users.usersEmail}", //result.orders.users.usersEmail ,
						buyer_name : result.orders.ordersReceiverName ,//"${orders.ordersReceiverName}", //result.orders.ordersReceiverName ,
						buyer_tel : result.orders.ordersReceiverPhone , ////"${orders.ordersReceiverPhone}", //result.orders.ordersReceiverPhone , //
						buyer_addr : result.orders.ordersAddr ,//"${orders.ordersAddr}", //result.orders.ordersAddr ,
						buyer_postcode : result.orders.ordersZipcode ,//"${orders.ordersZipcode}", //result.orders.ordersZipcode ,
						m_redirect_url : "http://localhost:8000/"
					}, //IMP.request_pay end 
					function(rsp){//callback
						if(rsp.success){
							alert("검증시작");
							//결제 성공 시: 결제 승인에 성공한 경우
							//일치하는지 검증하기(검증 메소드 컨트롤러에서 호출)
							$.ajax({
							url: "/verifyIamport",//${pageContext.request.contextPath}
				            type: "post",
// 				            dataType: "json", //리턴되는 값이 collection일때 사용
				            dataType: "text", 
				            data: {
				            	"${_csrf.parameterName}": "${_csrf.token}",
				                imp_uid: rsp.imp_uid,
// 				                merchant_uid: rsp.merchant_uid, //imp_uid로 조회 및 사용
// 				                orders: result.orders //"${orders}" //orders 다시 담는게 진행이 안되면 위의 merchant_uid로 정보 찾아서 진행하는걸로 할 것
				            },
							success: function(done){
								alert("주문 및 결제가 완료되었습니다");
							    //검증이후 페이지 이동:주문내역 페이지나 주문성공 페이지로 이동~~(1-1 : Orders컨트롤러의 마이페이지-주문내역출력 mapping url 또는 주문 성공 페이지)
								location.href="${pageContext.request.contextPath}/ordersTest/ordersResult";//&${_csrf.parameterName}=${_csrf.token}";
							}, //success end
							error: function(err){
								alert("3결제에 실패하였습니다");
							}//error end
							});//ajax end
						}//rsp.success end
						else{//(request,status,err)
							alert("2결제에 실패하였습니다.");
// 							alert(rsp.error);
// 							alert("code="+request.status+"\n"+"message"+request.responseText+"\n"+"error:"+err);
// 							$.ajax({
// 								url: "${pageContext.request.contextPath}/orders/delete", //${pageContext.request.contextPath}
// 								type: "post", 
// 								dataType: "text", //"json",
// 								data: {result.orders,
// 				            	"${_csrf.parameterName}": "${_csrf.token}"}
// 							},
// 							success: function(rs){alert("2결제실패끝");}
// 							error : function(request,status,err){
// 							alert("code="+request.status+"\n"+"message"+request.responseText+"\n"+"error:"+err);
// 							})//2실패 ajax끝
// 							alert("2결제실패 ajax 끝");
						}//else end
					}); //rsp,IMPrequest_pay end
				}, //success end
				error : function(request,status,err){
// 					$.ajax({
// 						url: "${pageContext.request.contextPath}/orders/delete", //${pageContext.request.contextPath}
// 						type: "text", //"post",
// 						dataType: "json",
// 						data: "${Orders}"
// 					})//1실패 ajax끝
					let msg = "1주문에 실패했습니다. 다시 주문해주세요.";
					alert(msg);
					alert("code="+request.status+"\n"+"message"+request.responseText+"\n"+"error:"+err);
				}//error end
			}); //ajax end
		});//맨 위의 #~~Btn 끝
}); //button function end
// }//function
</script>

<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                }

                var totalAddr=addr+' '+extraAddr+' ';
                
                // 우편번호를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = totalAddr;
                // 커서를 주소의 맨 뒤로 이동한다.
                document.getElementById("sample6_address").focus();
                document.getElementById("sample6_address").value = '';
                document.getElementById("sample6_address").value = totalAddr;
            }
        }).open();
    }
</script>
</head>
<body>
<!-- <input type="text" id="sample6_postcode" placeholder="우편번호"> -->
<!-- <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br> -->
<!-- <input type="text" id="sample6_address" placeholder="주소"><br> -->
<!-- <input type="text" id="sample6_detailAddress" placeholder="상세주소"> -->
<!-- <input type="text" id="sample6_extraAddress" placeholder="참고항목"> -->
<%-- action="${pageContext.request.contextPath}/주문으로" --%>
<form name="orders" id="ordersForm" method="post" >
<input type="text" name="ordersZipcode" id="sample6_postcode" placeholder="ZipCode">
<input type="button" onclick="sample6_execDaumPostcode()" value="Find ZipCode"><br>
<input type="text" name="ordersAddr" id="sample6_address" placeholder="Adress"><br>
<input type="text" name="ordersReceiverName" id="ordersReceiverName" placeholder="name"><br>
<input type="text" name="ordersReceiverPhone" id="ordersReceiverPhone" placeholder="phone"><br>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<%-- <input type="hidden" name="orderdetails" value="${orderdetails}"> --%>
<%-- <input type="hidden" name="productList" value="${productList}"> --%>
<%-- <input type="hidden" name="orderdetailsList" value="${orderdetailsList}"> --%>
<%-- <input type="hidden" name="cartList" value="${cartList}"> --%>

<input type="button" id="checkOutBtn" value="결제하기" >
</form>
<!-- onclick="checkout()" -->
</body>
</html>