$( function() {
          $("a[href='#' ]:contains('쪽지 보내기')").on("click" , function() {
        	var receiverId = $("#receiverId").val();
        	alert(receiverId);
        	window.open("/message/addMessage?receiverId="+receiverId,"addMessgeView","width=300, height=350,status=no, scrollbars=no, location=no");
          });
      });