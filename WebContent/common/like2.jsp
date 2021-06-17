<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
li > span{
	position: relative !important;
	color: #555555;
}
.block::before{
	color: #F05643 !important;
}
.full{
	color: #F05643 !important;
}
</style>
<script type="text/javascript">

var postNo = $("input[name='postNo']").val();

function addLike(){
	
	$.ajax({
		url: "../like/json/addLike/"+postNo,
		method: "POST",
		dataType: "json",
		success:function(returnData){
			getLike();
		}
	});	
}

function deleteLike(){
	
	$.ajax({
		url: "../like/json/deleteLike/"+postNo,
		method: "POST",
		dataType: "json",
		success:function(returnData){
			getLike();
		}
	});	
}


function getLike(){
	
	$.ajax({
		url: "../like/json/listLikeByPost/"+postNo,
		dataType: "json",
		success:function(returnData){
			$(".likeConut1").empty();
			$(".likeConut1").append(returnData.totalCount);
			
			$("span.likeIcon1").empty();

			if( ((JSON.stringify(returnData.list)).indexOf("${loginUser.userId}") == -1) || ("${loginUser.userId}"=='') || ("${loginUser.userId}" == null) ){
            	$("span.likeIcon1").append('<span class="far fa-heart"></span>');

            }else{
                $("span.likeIcon1").append('<span class="fas fa-heart full"></span>');
            }
		}
	});	
}

function addWish(contentid){
	$.ajax({
		url:"../wish/json/addWishFromTrip/"+contentid+"",
		method:"GET",
		dataType:"json",
		headers :{
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		success:function(returnData){
			var message = returnData.message;
			if(message == "ok"){
				makeToast("위시리스트 저장되었습니다.");
			}else{
				makeToast("이미 저장된 장소");
			}
		}
	});
}


function getWish(){
	
	$.ajax({
		url: "../like/json/listWishByPost/"+postNo,
		dataType: "json",
		success:function(returnData){
			$(".wishConut1").empty();
			$(".wishConut1").append(returnData.totalCount);
			
			$("span.wishIcon1").empty();

			if( ((JSON.stringify(returnData.list)).indexOf("${loginUser.userId}") == -1) || ("${loginUser.userId}"=='') || ("${loginUser.userId}" == null) ){
            	$("span.wishIcon1").append('<span class="far fa-bookmark"></span>');

            }else{
                $("span.wishIcon1").append('<span class="fas fa-bookmark full"></span>');
            }
		}
	});	
}


$(function(){

	$("a.likeIcon1").bind("click", function(){
		
		if( $("span.likeIcon1 svg").attr("data-prefix") == 'fas' ){
			deleteLike();
		}else{
			addLike();
		}
		
	});
	
	$("a.wishIcon1").bind("click", function(){
		
		if( $("span.wishIcon1 svg").attr("data-prefix") == 'fas' ){
			deleteWish();
		}else{
			addWish();
		}
		
	});
});

window.onload = function(){
//	getLike();
//	getWish();
}


</script>

<div style="padding: 30px">
<span style="padding-right: 15px">
<a href="#none" class="heart1"><span class="likeIcon1"></span>&nbsp;<span class="likeConut1"></span></a>
</span>
<span>
<a href="#none" class="wish1"><span class="wishIcon1">&nbsp;<span class="wishCount1"></span></span></a>
</span>
</div>
