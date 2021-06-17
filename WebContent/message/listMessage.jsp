<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
  

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>  
<script type="text/javascript">

	$( function() {
		//==> 추가된부분 : "addUser"  Event 연결
		
		$("a[href='#tabs-1']:contains('받은 쪽지함')").on("click" , function() {
			//alert("받은 쪽지함")
			//self.location= "/message/listMessage"
			listMessage();
			
		});
	});
	
	
	function listMessage(){
		$.ajax({
			url:"/message/json/listMessage/"+'${loginUser.userId}',
			method:"GET",
			dataType:"json",
			headers :{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},						
			success: function(returnData){
				$("tbody tr").remove();
				var message = returnData.list;
				var tableValue ="";
								
				
				for (var i = 0; i < message.length; i++) {
					tableValue = "<tr class='warning'>"
			          	+"<td><input type='checkbox' name='checkbox' id='checkbox' value='"
			          	+message[i].messageNo+"'></td>"
			          +"<td>"+message[i].senderId+"</td>"
			          +"<td>"+message[i].receiverId+"</td>"
			          +"<td>"+message[i].text+"</td>"
			          +"<td>"+message[i].regDate+"</td>"
			        +"</tr>";
					$("tbody").append(tableValue);
			    }
			}
		});
	}
	
	function listSendMessage(){
		$.ajax({
			url:"/message/json/listSendMessage/"+'${loginUser.userId}',
			method:"GET",
			dataType:"json",
			headers :{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},						
			success: function(returnData){
				$("tbody tr").remove();
				var message = returnData.list;
				var tableValue ="";
								
				
				for (var i = 0; i < message.length; i++) {
					tableValue = "<tr class='warning'>"
			          	+"<td><input type='checkbox' name='checkbox' id='checkbox' value='"
			          	+message[i].messageNo+"'></td>"
			          +"<td>"+message[i].senderId+"</td>"
			          +"<td>"+message[i].receiverId+"</td>"
			          +"<td>"+message[i].text+"</td>"
			          +"<td>"+message[i].regDate+"</td>"
			        +"</tr>";
					$("tbody").append(tableValue);
			    }
			}
		});
	}
	
	
	function listMessageToRead(){
		$.ajax({
			url:"/message/json/listMessageToRead/"+'${user.userId}',
			method:"GET",
			dataType:"json",
			headers :{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},						
			success: function(returnData){
				$("tbody tr").remove();
				var message = returnData.list;
				var tableValue ="";
								
				
				for (var i = 0; i < message.length; i++) {
					tableValue = "<tr class='warning'>"
			          	+"<td><input type='checkbox' name='checkbox' id='checkbox' value='"
			          	+message[i].messageNo+"'></td>"
			          +"<td>"+message[i].senderId+"</td>"
			          +"<td>"+message[i].receiverId+"</td>"
			          +"<td>"+message[i].text+"</td>"
			          +"<td>"+message[i].regDate+"</td>"
			        +"</tr>";
					$("tbody").append(tableValue);
			    }
			}
		});
	}
	
	
	$( function() {
		//==> 추가된부분 : "addUser"  Event 연결
		$("a[href='#tabs-1']:contains('보낸 쪽지함')").on("click" , function() {
			//alert("보낸 쪽지함")
			//self.location ="/message/listSendMessage"
			listSendMessage();
		});
	});
	
	$( function() {
		//==> 추가된부분 : "addUser"  Event 연결
		$("a[href='#tabs-1']:contains('읽을 쪽지함')").on("click" , function() {
			//alert("읽을 쪽지함")
			//self.location ="/message/listMessageToRead"
			listMessageToRead();
		});
	});
	
	
	$(function(){
		$(document).on("click","tr td:nth-child(4)", function(){
		//$("tr td:nth-child(4)").on("click",function(){
			var messageNo = $($("input[name='checkbox']")[$("tr td:nth-child(4)").index(this)]).val();
			//alert(messageNo)
			window.open("/message/getMessage?messageNo="+messageNo,"getMessge","width=400, height=360,status=no, scrollbars=no, location=no");
		});
	});
	
	
	
	function deleteMessage(messageNoList){
		$.ajax({
			url:"/message/json/deleteMessage/"+messageNoList,
			method:"GET",
			dataType:"json",
			headers :{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},						
			success: function(returnData){
				var count = returnData.count;
				alert(count+"개의 메시지가 삭제되었습니다");
				
				for (var i = 0; i < count; i++) {
					$($("input[name=checkbox]:checked").parents("td")).parents("tr").remove();					
				}
				
				
			}
			
		});
	}
	
	$(function(){
		$("button[type='button']:contains('삭제')").on("click",function(){
			//alert("삭제");
			var checkedMessageCount = $("input[name=checkbox]:checked").length;
			var messageNoList="";
			
			
			for (var i = 0; i < checkedMessageCount; i++) {
				if(i != checkedMessageCount-1){
					var value = $($("input[name=checkbox]:checked")[i]).val()+","; 
				}else{
					var value = $($("input[name=checkbox]:checked")[i]).val();
				}
				
				messageNoList += value;
			
			}
			
			//alert(messageNoList);
			deleteMessage(messageNoList);
		});
		
		
		$("header h1").on("click", function(){
			self.location = "../message/listMessage";
		});
		
	});
	
</script>
<!--  -->
<style >
body {
	background: #ffffff;
}
#tabs{
	margin-left : 10%;
	margin-right: 10%;
}
.ui-tabs-nav.ui-corner-all.ui-helper-reset.ui-helper-clearfix.ui-widget-header{
	padding: :0px;

}
.ui-tabs.ui-corner-all.ui-widget.ui-widget-content{
	padding: :0px;
}

#header{
	background-image: url("/resources/images/tripIcon/desktop-background-3091211_1920.jpg");
}

#nav{
	background-image: url("../resources/helios/images/header.jpg");	
	background: #6d4532;
	opacity: 0.7;
}
.ui-tabs.ui-corner-all.ui-widget.ui-widget-content{
	padding: 0px;
}
.ui-tabs-panel.ui-corner-bottom.ui-widget-content{
	background:#400f0f; 
}
.ui-tabs-nav.ui-corner-all.ui-helper-reset.ui-helper-clearfix.ui-widget-header{
	background: #c1a8b1;
	padding: 0px;
}
.ui-tabs-tab.ui-corner-top.ui-state-default.ui-tab.ui-tabs-active.ui-state-active{
	background: #400f0f;
}

th{
	background: #ca9975;
}
.ui-tabs-tab.ui-corner-top.ui-state-default.ui-tab{
	background: #e6bfa2;
}


</style>
<title>Insert title here</title>
</head>
<body class="no-sidebar">
		<div id="page-wrapper">

			<div id="header">

				<div class="inner">
					<header>
						<h1>쪽지함</h1>
					</header>
				</div>
						
				<jsp:include page="/layout/toolbar.jsp" />

			</div>
			
			<!-- 
			
		 -->
		 	</br>
			<div class="container">
		  		<div class="row">
		  			<div class="col-md-12">
					<div id="tabs">
					  <ul>
					  	<a href="#tabs-1">받은 쪽지함</a>&nbsp;&nbsp;&nbsp;
					    <a href="#tabs-1">보낸 쪽지함</a>
					  </ul>
					  
					  
					  <div id="tabs-1">
					    <table class="table">
					      <thead>
					        <tr>
					          <th></th>
					          <th>보낸 사람</th>
					          <th>받는 사람</th>
					          <th>내용</th>
					          <th>보낸 날짜</th>
					        </tr>
					      </thead>
					      <tbody>
					      <c:forEach var ="list" items="${list}">
						        <tr class="warning">
						          <td><input type="checkbox" name="checkbox" id="checkbox" value="${list.messageNo}"></td>
						          <td>${list.senderId}</td>
						          <td>${list.receiverId}</td>
						          <td>${list.text}</td>
						          <td>${list.regDate}</td>
						        </tr>
					        </c:forEach>  
					        
					      </tbody>
					      
					    </table>
					    <button type="button" class="btn btn-danger">삭제</button>
					  </div>
				  </div><!-- tab end -->
			   </div>><!-- grid end -->
			</div><!-- row end -->
		</div><!-- container end -->
	</div><!-- page end -->
	<script src="../resources/helios/assets/js/jquery.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
		<script src="../resources/helios/assets/js/skel.min.js"></script>
		<script src="../resources/helios/assets/js/util.js"></script>
		<script src="../resources/helios/assets/js/main.js"></script>
</body>
</html>