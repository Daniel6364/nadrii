<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
<script type="text/javascript">
	$(function(){
		$("a[href='#']:contains('답장')").on("click",function(){
			var receiverId = $("input[name='senderId']").val();
			self.location="/message/addMessage?receiverId="+receiverId;
		});
		
		$("a[href='#']:contains('취소')").on("click",function(){
			window.close();
		});
		
	});
	


</script>
<style type="text/css">
* {
	background: #ffffff;
}
input:focus{
				outline:none;
			}
</style>
<title>Send Message</title>
</head>
<body>
	<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-2">
						

						<div id="sendmessage"></div>
						<div id="errormessage"></div>
						<form>
							<div role="alert" style="padding-top: 10px; padding-bottom: 5px; font-size: 15pt;">
							    <strong>보내는 사람 : </strong>
							    	${message.senderId}
							    	<input type="hidden" name="senderId" value="${message.senderId}"/>
    						</div>
							
							
							<div class="form-group">
								<div role="alert" style="padding-top: 5px;padding-bottom: 5px; font-size: 15pt;">
							    <strong>받는 사람 : </strong>
							    	${message.receiverId}
							    	<input type="hidden" name="receiverId" value="${message.receiverId}"/>
	
    						</div>
								<div class="validation"></div>
							</div>
							
							<div class="form-group">
								<textarea class="form" style="width: 370px; font-size: 15pt;" name="text" rows="5" data-rule="required" data-msg="Please write something for us" readonly="readonly" >${message.text}</textarea>
								<div class="validation"></div>
							</div>

							<div class="text-center">
								<a class="button" href="#" role="button" style="float: left; width: 30%;">답장</a>
								<a class="button" href="#" role="button" style="float: right; width: 30%;">취소</a>
							</div>							
						</form>
					</div>
				</div>
			</div>	
</body>
</html>