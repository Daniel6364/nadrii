<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">
	#commentContainer{
		padding-bottom: 10px;
	}
	div[name='submitComment']{
		background: #4b3753;
	}
	img.img-circle{
		width: 36px;
		height: 36px;
	}
</style>
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
<script type="text/javascript"> 
	var postNo = $('input[name="postNo"]').val();
	function listComment() {
		$.ajax({
			url:"/common/listCommentByPost",
			method:"GET",
			data:{
				"postNo": postNo
			},
			success: function(JSONData) {
				$('#commentContainer').empty();
				for(i=0;i<JSONData.totalCount;i++){
					var html = '<div class="comments"><span class="col-xs-1" style="float: left; padding: 0;"><input type="hidden" name="commentNo" value="'
					+JSONData.listComment[i].commentNo
					+'">'
					if(JSONData.listComment[i].user.profileImageFile == null){
						html += '<img src="/resources/images/00742106_105752.jpg" alt="${user.userId}" class="img-circle" width="40px" height="40px"></span><span class="col-xs-15" style="padding-left: 30px;"><span style="color: black;">'
					}else{
						html += '<img src="'+JSONData.listComment[i].user.profileImageFile+'" alt="${user.userId}" class="img-circle" width="40px" height="40px"></span><span class="col-xs-15" style="padding-left: 30px;"><span style="color: black;">'
					}
					html += JSONData.listComment[i].user.userId
					+'</span>&nbsp;<span style="color: gray; font-size:10pt; padding: 0;">'
					+(JSONData.listComment[i].regDate).slice(0, -2)
					+'</span><br/><span class="text" style="padding-left: 30px;">'+JSONData.listComment[i].text+'</span></span><span class="col-xs-2 edit" style="float: right; padding: 0;">';
					html += '<span class="fas fa-edit" style="font-size:10pt;"/>&nbsp;&nbsp;<span class="fas fa-trash" style="font-size:10pt;"/>';
					html += '</span></div><hr style="margin-bottom: 5em; position: absolute; border: 0; top: 0; height: 0;"/>'
					$('#commentContainer').append(html);
										
					if("${loginUser.userId}" != JSONData.listComment[i].user.userId){
						$($("span.fas.fa-edit")[i]).css("display", "none");
						$($("span.fas.fa-trash")[i]).css("display", "none");
					}
				}
				$(".comment").empty();
				$(".comment").append(JSONData.totalCount);
				
				
			}
			
		});
	}
	function addComment() {
		var userId = $('input[name="userId"]').val();
		var text=$('input[name="text"]').val();
		$.ajax({
					url:"/common/addComment",
					dataType:"json",
					method:"POST",
					headers : {
						"Accept" : "application/json",
					},
					data:{
						"postNo": postNo,
						"text": text,
						"userId": userId
					},
					success: function(JSONData) {
						$('input[name="text"]').val("");
						$('#commentContainer').empty();  
						listComment();
					},
					error: function() {
						alert("알 수 없는 오류가 발생했습니다.")
					}
		})
	}
	function updateComment(commentNo, text) {
		$.ajax({
			url: '/common/updateComment',
			method:'POST',
			headers:{
				"Accept" : "application/json",
			},
			data:{
				"commentNo": commentNo,
				"text": text
			},
			success: function() {
				$('input[name="text"]').val("");
				$('#commentContainer').empty();
				alert('수정을 완료했습니다.');
				listComment();
			},
			error: function() {
				$('input[name="text"]').val("");
				$('#commentContainer').empty();
				alert("수정을 실패했습니다.");
				listComment();
			}
			
		})
	} 
		
	$(function() {
		
		
		$('body').load('like.jsp', function() {
			getLike();
			listComment();
		})
		
		$('div[name="submitComment"]').bind('click', function() {
			addComment();
		});
		
		var text;
		var commentNo;
		
		$('#commentContainer').on('click','.fa-edit',function() {
			text = $($('span.text')[$(".fa-edit").index(this)]).html();
			commentNo = $($('input[name="commentNo"]')[$(".fa-edit").index(this)]).val();
			alert(commentNo);
			var editForm = '<span style="position: relative; float: left; width: 70%; padding-left: 25px"><input name="editText" class="form-control" type="text" value="'+text+'"/></span><div class="button" style="float: right; position: relative; padding: 0; font-size: 0.7em; width: 12%; height: 1.8%" name="update">수정</div>';
			$($('span.text')[$(".fa-edit").index(this)]).html(editForm);
			$($('span.edit')[$(".fa-edit").index(this)]).css("overflow", "hidden");
			$($('span.edit')[$(".fa-edit").index(this)]).empty();
			$($('div.comments')[$(".fa-edit").index(this)]).attr("style", "margin-bottom: -10em;");
			$($('span.text')[$(".fa-edit").index(this)]).removeAttr("style");
		});
		
		$('#commentContainer').on('click', 'div[name="update"]', function() {
			alert(commentNo);
			text = $('input[name="editText"]').val();
			updateComment(commentNo, text);
		});
		
		$('#commentContainer').on('click', '.fa-trash',function() {
			if(confirm("삭제하시겠습니까?")==true){
				var commentNo = $($('input[name="commentNo"]')[$(".fa-trash").index(this)]).val();
				$.ajax({
					url: '/common/deleteComment',
					method:'POST',
					data:{
						"commentNo": commentNo,
						"postNo": postNo
					}, 
					success: function(JSONData) {
						$('input[name="text"]').val("");
						$('#commentContainer').empty();
						alert('삭제를 완료했습니다.');
						listComment();
					},
					error: function() {
						$('input[name="text"]').val("");
						$('#commentContainer').empty();
						alert("삭제에 실패했습니다.");
						listComment();
					}
					
				});
			}else{
				return;
			}
		})
	})
</script>

<body onload="javascript:listComment();">
	<div>
		<div>
			<form name="formData">
				<input type="hidden" name="userId" value="${loginUser.userId}">
				<div style="float: left; width: 83%;">
					<input type="text" name="text" class="form-control" placeholder="댓글을 입력하세요...">
				</div>
			</form>
		</div>
		<div class="button" style="float: right; position: relative; padding: 0; font-size: 12pt; width: 15%;" name="submitComment">submit</div>
		<br/><br/><br/>
		<div id="commentContainer"></div>
	</div>
</body>













