<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Summernote</title>
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet"> 
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
  <style type="text/css">
  	.selectedhashtagButtons {
			  font-size:15px;
			  font-family: 'Nanum Gothic';
			  color: white;
			  text-align: center;
			  background: #2fade2;
			  border-radius: 30px;
			  padding:0 1% 0 1%;
	}
	.hashtagButtons {
			  font-size:15px;
			  font-family: 'Nanum Gothic';
			  color: black;
			  text-align: center;
			  background: #edeeef;
			  border-radius: 30px;
			  padding:0 1% 0 1%;
	}
	#selectedThumbnail{
		border: solid 2px red;
	}
  </style>
  <script type="text/javascript">
        /* summernote에서 이미지 업로드시 실행할 함수 */
	 	function sendFile(file, editor) {              
            // 파일 전송을 위한 폼생성
	 		data = new FormData();
	 	    data.append("uploadFile", file);
	 	    $.ajax({ // ajax를 통해 파일 업로드 처리
	 	        data : data,
	 	        type : "POST",
	 	        url : "uploadImage",
	 	        cache : false,
	 	        contentType : false,
	 	        processData : false,
	 	        success : function(data) { // 처리가 성공할 경우
                    // 에디터에 이미지 출력

	 	        	$(editor).summernote('editor.insertImage', "\n\n"+data.relativeUrl+"\n\n");
	 	        if($('#cndThumbnail').html().indexOf('<img') == -1){
	 	        	$('div#cndThumbnail').html('<img class="cndThumbnail" border="2" alt="'+data.relativeUrl+'" src="'+data.relativeUrl+'" width="100px" height="120px"/>&nbsp;')
	 	        }else{
	 	      		$('div#cndThumbnail').append('<img class="cndThumbnail" border="2" alt="'+data.relativeUrl+'" src="'+data.relativeUrl+'" width="100px" height="120px"/>&nbsp;')
	 	        }
	 	        	listHashTag(data.url);
	 	        },
	 	        error : function() {
					alert("파일 업로드에 실패했습니다.")
				}
	 	    });
	 	}
        function deleteFile(file) {
			
		}
        var hashtagList=",";
        function listHashTag(filePath) {
        	$.ajax({
        		url : "listHashTag",
        		type : "POST",
        		data : {
        			"filePath" : filePath
        		},
        		success : function(data) {
        			for(i=0;i<data.length;i++){
        				if(data[i]==("음식")){
        					data[i] = "맛집"
        				}
        				var hashtagsTemp=',';
        				$('button.selectedhashtagButtons').each(function() {
        					hashtagsTemp += $(this).val()+',';
        				})
        				$('button.hashtagButtons').each(function() {
        					hashtagsTemp += $(this).val()+',';
        				})
        				console.log(hashtagsTemp)
        				if(hashtagsTemp.indexOf(','+data[i]+',') == -1){
	 	        			$("#cndHashTags").append('<button type="button" class="hashtagButtons" value="'+data[i]+'"><span class="fas fa-plus"></span>&nbsp;#'+data[i]+'</button>&nbsp;');
        				}
	 	        	}
				},
				error : function() {
					$("#cndHashtags").append("<p>분석 결과를 찾을 수 없습니다.</p>")
				}
        	})
        }
        function addHashTag(value) {
        	$('button.hashtagButtons[value="'+value+'"]').html('<span class="fas fa-check-circle"></span>&nbsp;#'+value);
			$('button.hashtagButtons[value="'+value+'"]').attr('class', 'selectedhashtagButtons');
		}
        function deleteHashTag(value) {
        	$('button.selectedhashtagButtons[value="'+value+'"]').html('<span class="fas fa-plus"></span>&nbsp;#'+value);
			$('button.selectedhashtagButtons[value="'+value+'"]').attr('class', 'hashtagButtons');
		}
        $(function() {
	 	    
 	 	   $('div#cndHashTags').on('click', 'button.hashtagButtons', function() {
 	 		   addHashTag($(this).val());
 			})
 			
  			$('div#cndThumbnail').on('click', 'img.cndThumbnail', function() {
  				$('div#cndThumbnail>img').removeAttr("id")
  				$('input[name="thumbNailFileName"]').val($(this).attr('src'));
  				$(this).attr("id", "selectedThumbnail");
  				console.log($('input[name="thumbNailFileName"]').val());
 			}) 
 			
 			$('div#cndHashTags').on('click', 'button.selectedhashtagButtons', function() {
 	 		   deleteHashTag($(this).val());
 			})
 			
 			$('#inputGroupSuccess1').keydown(function(key) {
 				var data = $(this).val().replace(/ /g, '');
 				if(key.keyCode==13){
 					if(data!=""){
 		 				$(this).val("");
 						$("#cndHashTags").append('<button type="button" class="selectedhashtagButtons" value="'+data+'"><span class="fas fa-check-circle"></span>&nbsp;#'+data+'</button>&nbsp;');
 					}else{	
 	 					alert("추가하실 해시태그를 입력해주세요");
 					}
 				}
			})
			$('#inputGroupSuccess1').bind('keyup input',function(key) {
				var keyword = $('#inputGroupSuccess1').val();
				if(key.keyCode==13){
					keyword="";
				}
				$.ajax({
					url:"/comm/getHashtags",
					data:{
						"keyword": keyword
					},
					method:"POST",
					success: function(JSONData) {
						console.log(JSONData)
						var availableTags = JSONData
						$("#inputGroupSuccess1").autocomplete({
						      source: availableTags
						 });
					},
					error: function() {
						alert("오류")
					}
				})
		 	})
	 	})
	</script>
</head>
<body>
		<form name="textForm">
		<textarea id="summernote" name="text">${community.text}</textarea>
        <script>
	        var openDialog = function (uri, name, options, closeCallback) {
			    var win = window.open(uri, "", options);
			    var interval = window.setInterval(function() {
			        try {
			            if (win == null || win.closed) {
			            	
			                window.clearInterval(interval);
			                closeCallback(win);
			            }
			        }
			        catch (e) {
			        }
			    }, 1000);
			    return win;
	        }
        	var addPlace = function (context) {
        	 	var ui = $.summernote.ui;

	        	var button = ui.button({
	        		contents: '<i class="fas fa-map-marker-alt"/> Place',
	        		click: function() {
	    				//openWin = window.open(popUrl,"",popOption);    
	    				    var uri = "addPlace.jsp";
		    				var options = "width=800, height=600, resizable=no, scrollbars=no, status=no;"
	    				    openDialog(uri, "", options, function(win) {
	    				    	
	    				    	var content = $('#content_pr').val();
	    				    	var lat = $('#lat').val();
	    				    	var lng = $('#lng').val();
	    				    	if(content == null || content=='' || lat=="," || lng == ","){
									return;
	    				    	}else{ 
	    				    		console.log($('#summernote').summernote('code'));
	    				    		var html =$('#summernote').summernote('code')+'<button type="button" class="placeButton">'+
									'<span class="col-xs-3">'+
									'<img src="../resources/images/marker/marker_uc.png" width="50px" height="80px">'+
									'</span>'+
									'<span class="col-xs-9">'+$('#content_pr').val()+'</button>'+
									'</span><br/><p style="text-align: center;"><br><br></p>';
				    				$('#summernote').summernote('code', html);
	    				    	}
						})
	    			}
	        	}) 
	        	return button.render();
        	}
			var openWin;
            $(document).ready(function() {
                $('#summernote').summernote({
                    height: 800,
                    toolbar: [
                    	['style', ['bold', 'italic', 'underline', 'clear']],
                        ['fontsize', ['fontsize']],
                        ['color', ['color']],
                        ['para', ['ul', 'ol']],
                        ['height', ['height']],
                        ['insert', ['picture']],
                    	['mybutton', ['place']]
                    ],
                    buttons: {
                       place: addPlace
                    },
					callbacks: { // 콜백을 사용
                        // 이미지를 업로드할 경우 이벤트를 발생
					    onImageUpload: function(files, editor, welEditable) {
					    	for(i=0;i<files.length;i++){
					    		sendFile(files[i], this);
					    	}
						}
					}
				});
                $('#summernote').summernote('justifyCenter');
			});
		</script>
		<input type="hidden" name="hashtag" value="${community.hashtag}">
		<input type="hidden" name="lat" id="lat" value="${community.lat}">
		<input type="hidden" name="lng" id="lng" value="${community.lng}">
		<input type="hidden" name="thumbNailFileName" value="${community.thumbNailFileName}">
		<input type="hidden" id="content_pr">
		<h5 align="left">썸네일을  선택해주세요</h5>
		<div id="cndThumbnail" align="center">
		<c:if test="${empty community.thumbNailFileName}">
			<span style="padding-left: 10px; font-color: gray; font-size:15px;">게시물에 등록하신 이미지 중 선택하실 수 있습니다.</span>
		</c:if>
		<c:if test="${! empty community.thumbNailFileName}">
			<img id="selectedThumbnail" src="${community.thumbNailFileName}" width="100px" height="120px">
		</c:if>
		</div>
		<br/>
		<h5 align="left">해시태그</h5>
			 <div class="ui-widget">
				 <div class="input-group">
		    	 	<span class="input-group-addon">#</span>
		    		<input class="form-control" id="inputGroupSuccess1" aria-describedby="inputGroupSuccess1Status">
		 		 </div>
	 		 </div>
		</form>
</body>
<style type="text/css">
		input[type="submit"],
		input[type="reset"],
		input[type="button"],
		button,
		.button {
		-moz-appearance: none;
		-webkit-appearance: none;
		-ms-appearance: none;
		appearance: none;
		-moz-transition: background-color 0.2s ease, box-shadow 0.2s ease, color 0.2s ease;
		-webkit-transition: background-color 0.2s ease, box-shadow 0.2s ease, color 0.2s ease;
		-ms-transition: background-color 0.2s ease, box-shadow 0.2s ease, color 0.2s ease;
		transition: background-color 0.2s ease, box-shadow 0.2s ease, color 0.2s ease;
		background-color: #FFFFFF;
		border: 0;
		box-shadow: inset 0 0 0 1px rgba(160, 160, 160, 0.3);
		color: #3c3b3b;
		cursor: pointer;
		display: inline-block;
		font-family: "Raleway", Helvetica, sans-serif;
		font-size: 0.6em;
		font-weight: 800;
		width: 4em !important;
		height: 2.8125em;
		letter-spacing: 0.25em;
		line-height: 2.8125em;
		padding: 0;
		text-align: center;
		text-decoration: none;
		text-transform: uppercase;
		white-space: nowrap;
	}
	.note-mybutton > .note-btn{
		width: 8em !important;
	}
	</style>
</html>