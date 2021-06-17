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
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
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

var likeCount;

function getLike(){
	
	$.ajax({
		url: "../like/json/listLikeByPost/"+postNo,
		dataType: "json",
		success:function(returnData){
			$(".likeConut").empty();
			$(".likeConut").append(returnData.totalCount);
			
			$("span.likeIcon").empty();

			if( ((JSON.stringify(returnData.list)).indexOf("${loginUser.userId}") == -1) || ("${loginUser.userId}"=='') || ("${loginUser.userId}" == null) ){
            	$("span.likeIcon").append('<span class="far fa-heart"></span>');

            }else{
                $("span.likeIcon").append('<span class="fas fa-heart full"></span>');
            }
		}
	});	
}

$(function(){

	$("a.heart").bind("click", function(){
		
		if( $("span.likeIcon svg").attr("data-prefix") == 'fas' ){
			deleteLike();
		}else{
			addLike();
		}
		
	});
	
});

</script>

<div style="padding: 30px">
<span style="padding-right: 15px">
<a href="#none" class="heart"><span class="likeIcon"></span>&nbsp;<span class="likeConut"></span></a>
</span>
<span>
<span class="icon fa-comment">&nbsp;<span class="comment"></span></span>
</span>
</div>
