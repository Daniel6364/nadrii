<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Summernote</title>
  <!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">  -->
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet"> 
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
  
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
	 	        	$(editor).summernote('editor.insertImage', "\n\n"+data.url+"\n\n");
	 	      		$('div#cndThumbnail').append('<img class="cndThumbnail" border="2" alt="'+data.url+'" src="'+data.url+'" width="100px" height="120px"/>&nbsp;')
	 	        	listHashTag(data.url);
	 	        },
	 	        error : function() {
					alert("파일 업로드에 실패했습니다.")
				}
	 	    });
	 	}
        function deleteFile(file) {
			
		}
        function addThumbnail(filePath) {
			$('input[name="thumbNailFileName"]').val(filePath);
			$('img.cndThumbnail[value="'+filePath+'"]').attr("class", "selected");
			console.log($('input[name="thumbNailFileName"]').val());
		}
        
        $(function() {
	 	    			
  			$('div#cndThumbnail').on('click', 'img.cndThumbnail', function() {
  				addThumbnail($(this).attr('src'));
 			});
		});
	</script>
</head>
<body>
		<form name="textForm">
		<textarea id="summernote" name="text">${group.text}</textarea>
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
	    				    var uri = "./addPlace.jsp";
		    				var options = "width=800, height=600, resizable=no, scrollbars=no, status=no;"
	    				    openDialog(uri, "", options, function(win) {
	    				    	var html =$('#summernote').summernote('code')+
	    									'<div style="font-size: 12pt; text-align: center; width: 330px; border: 1px solid;">'+$('#content_pr').val()+'</div></br></br></br>';
	    				    	$('#summernote').summernote('code', html);
						})
	    			}
	        	}) 
	        	
	        
		    	
	        	return button.render();
        	}
        	
			var openWin;
            $(document).ready(function() {
                $('#summernote').summernote({
                    height: 500,
                    toolbar: [
                    	['style', ['bold', 'italic', 'underline', 'clear']],
                        ['fontsize', ['fontsize']],
                        ['color', ['color']],
                        ['para', ['ul', 'ol', 'paragraph']],
                        ['height', ['height']],
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
			});
		</script>
		<input type="hidden" name="lat" id="lat">
		<input type="hidden" name="lng" id="lng">
		<input type="hidden" id="content_pr" name="content_pr">
		<input type="hidden" name="placeName" id="placeName_pr">
		<input type="hidden" name="placeDetail" id="placeDetail_pr">
		<div id="cndThumbnail"></div>
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