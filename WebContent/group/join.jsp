<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.fit {
	    width: 100% !important;
}
</style>
<script>

var groupNo = ${group.join.groupNo};

function addJoin(){
	
	$.ajax({
		url: "../join/json/addJoin/"+groupNo,
		method: "POST",
		dataType: "json",
		success:function(returnData){
			$('.userIdContainer').empty();
			getMemberList();			
		}
	});	
}

function deleteJoin(){
	$.ajax({
		url: "../join/json/deleteJoin/"+groupNo,
		method: "POST",
		dataType: "json",
		success:function(returnData){
			$('.userIdContainer').empty();
			getMemberList();	
		}	
	});	
}

function clickProfile1(){
	alert($($("input[type='hidden']")[memberIndex]).val());
}

function clickMessage1(){
	window.open("/message/addMessage?receiverId="+$($("input[type='hidden']")[memberIndex]).val(),"addMessgeView","width=400, height=360,status=no, scrollbars=no, location=no");
//	alert($($("input[type='hidden']")[memberIndex]).val());
}

var memberIndex;

function trClick(tr) {

	memberIndex = tr.rowIndex - 1;

}

function getMemberList(){
	
	$.ajax({
		url: "../join/json/getJoinMemberList/"+groupNo,
		method: "GET",
		dataType: "json",
		success:function(returnData){
			console.log(returnData);
			$('.totalCount').empty();
			$(".totalCount").append("<sub>총 참여자 수 "+returnData.totalCount+"명</sub>");
									
			for(var i=0; i<returnData.totalCount; i++){
				
				$(".userIdContainer").append('<tr onclick="javascript:trClick(this);"><td><input type="hidden" class="member" value="'+returnData.list[i].user.userId+'"><a href="#none" class="joinMemberId" data-container="body" data-toggle="popover">'+returnData.list[i].user.userId+'</a></td></tr>');
				console.log( returnData.list[i].user.userId == '${loginUser.userId}' )
				if( returnData.list[i].user.userId == '${loginUser.userId}' ){
					$('.joinButtonContainer').empty();
					$(".joinButtonContainer").append('<a href="#none" class="button fit delete" name="cancel" style="float: right">cancel</a>');
				}else{
					$('.joinButtonContainer').empty();
					$(".joinButtonContainer").append('<a href="#none" class="button fit modify" name="join" style="float: right">join</a>');
				}
				
				if( '${group.join.user.userId}' == returnData.list[i].user.userId ){
					$(".joinMemberId").append(" (master)");					
				}
				
			}
			
			$('[data-toggle="popover"]').popover({ 
				html: true,
				container: 'body',
				content: '<a href="#none" class="message" onclick="javascript:clickMessage1()"><span class="fas fa-envelope"></span> 쪽지 보내기 </a>',
				placement: 'bottom',
			});	
			
			$("a[name='join']").on("click", function(){
				addJoin();
			});	
			
			if('${loginUser.userId}' == '${group.join.user.userId}'){
				$('.joinButtonContainer').empty();
				$(".joinButtonContainer").append('<a href="#none" class="button fit delete" name="cancel" style="float: right">cancel</a>');
			}

			$("a[name='cancel']").on("click", function(){
				if('${loginUser.userId}' == '${group.join.user.userId}'){
					alert("글쓴이는 모임 참여 취소를 할 수 없습니다.");
					return;
				}
				if(confirm("참여를 취소하시겠습니까?")==true){
					deleteJoin();
					alert("취소되었습니다.");
				}else{
					return;	
				}	
			});	
		}
	});	
}
$(function() {
	if('${loginUser.userId}' == null || '${loginUser.userId}' == ''){
		alert("로그인 후 이용해주세요.")
		self.location="../user/loginView.jsp"
	}
})

</script>
</head>
<body>
	<table class="default">

		<thead>
			<tr>
				<th><span class="fas fa-users"></span>&nbsp;&nbsp;참여자 목록 <div class="totalCount" style="position: relative; float: right;"></div></th>
			</tr>
		</thead>
		
		<tbody class="userIdContainer"></tbody>
		
		<tfoot>
			<tr>
				<td class="joinButtonContainer" style="padding: 1em 2em 1em 2em;"></td>
			</tr>
		</tfoot>

	</table>
</body>
</html>