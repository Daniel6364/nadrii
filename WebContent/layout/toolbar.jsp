<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	//========== '나들이 정보' 버튼 처리 Event ==========
	$(function() {
		/*
		$("a[href='#' ]:contains('나들이 정보')").on("click", function() {
			self.location = "../trip/getTheme";
		});

		$("a[href='#' ]:contains('나만의 나들이')").bind("click", function() {
			self.location = "../comm/listComm";
		});

		$("a[href='#' ]:contains('나들이 플래너')").bind("click", function() {
			self.location = "../planner/getUserPlannerList";
		});

		$("a[href='#' ]:contains('나들이 티켓')").bind("click", function() {
			self.location = "../ticket/listTicket";
		});

		$("a[href='#' ]:contains('나들이 모임')").bind("click", function() {
			self.location = "../group/listGroTicket";
		});
		*/
	});

	$(document).ready(function() {
		// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
		$(".dropbtn01").click(function() {
			var submenu = $(this).next("ul");

			// submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
			if (submenu.is(":visible")) {
				submenu.slideUp();
			} else {
				submenu.slideDown();
			}
		});
	});

	$(document).ready(function() {
		// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
		$(".dropbtn02").click(function() {
			var submenu = $(this).next("ul");

			// submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
			if (submenu.is(":visible")) {
				submenu.slideUp();
			} else {
				submenu.slideDown();
			}
		});
	});

	//============="login" ==============

	function validateCheck() {
		var id = $("#userId").val();
		var password = $("#password").val();
		//alert(id+ ":::"+password);

		if (id == null || id == "") {
			alert("아이디 입력해라");
			return;
		}

		if (password == null || password == "") {
			alert("비번은 쳐야지 그래야 안쳐맞지")
			return;
		}

		$("form").attr("method", "POST").attr("action", "/user/login").submit();
	}

	//============= '로그인' 버튼 클릭 Event 처리 =============
	$(function() {
		$(".btn.btn-primary.joinBtn:contains('로 그 인')").bind("click",
				function() {
					alert("로그인");
					validateCheck();
				});
	});

	//============= "장바구니" 화면이동 =============
	$(function() {
		$("a[href='#']:contains('Logout')").bind("click", function() {
			alert("나갈거임");
			self.location = "/user/logout";
		});
	});

	//================= '로그아웃' 버튼 클릭 Event 처리========================

	$(function() {
		$("a[href='#' ]:contains('로그아웃')").on("click", function() {
			if (confirm("로그아웃을 하시겠습니까?")) {
				self.location = "/user/logout";
			}
		});
	});

	//================= '추가정보 입력' 버튼 클릭 Event 처리========================

	$(function() {
		$("a[href='#' ]:contains('추가정보 입력')").on("click", function() {
			self.location = "/user/addUserViewPlus.jsp";
		});
	});

	//================= '내정보 보기' 버튼 클릭 Event 처리========================

	$(function() {

		$("a[href='#' ]:contains('내 정보 보기')").on("click", function() {
			self.location = "/user/getUser"
		});
	});

	//================= '내정보 보기' 버튼 클릭 Event 처리========================

	$(function() {
		$("a[href='#' ]:contains('내 정보 수정')").on("click", function() {
			self.location = "/user/updateUser?userId=${loginUser.userId}";
		});
	});

	//================= '회원 리스트' 버튼 클릭 Event 처리========================

	$(function() {
		$("a[href='#' ]:contains('회원 리스트')").on("click", function() {
			self.location = "/user/listUser.jsp";
		});
	});

	//==================='쪽지함으로 이동' =============================

	$(function() {
		$("a[href='#']:contains('쪽지')").on("click", function() {
			self.location = "/message/listMessage";
		})
	})
</script>

<style type="text/css">
#nav {
	background-color: #3b2b48;
	opacity: 0.7;
}

#nav>ul>li {
	color: lightgray;
}
</style>
<!-- Nav -->
<nav id="nav">
	<ul>
		<c:if test="${  empty loginUser }">
			<!--  <li><a href="#">Login</a></li> -->
		</c:if>
		<c:if test="${ ! empty loginUser }">
			<li>
				<!--<a href="/user/getUser?userId=${loginUser.userId}"> --> <!-- <img src="/resources/images/00742106_105752.jpg" alt="..." class="img-circle" width="30px" height="30px"> -->
				<span> <c:if test="${ loginUser.userName eq null }">
											${ loginUser.userId }
										</c:if> ${ loginUser.userName } 님 ▼
			</span>
				<ul>
					<c:if test="${! isAdmin eq 'user' }">
						<li><a href="#">회원 리스트</a></li>
					</c:if>
					<li><a href="#">내 정보 보기</a></li>
					<li><a href="#">내 정보 수정</a></li>
					<li><a href="#">쪽지</a></li>
					<li><a href="#">로그아웃</a></li>
				</ul> <!--  </a>-->
			</li>
		</c:if>
		<li><a href="/index.jsp">H O M E</a></li>
		<li><a href="/trip/getTheme">나들이 정보</a></li>
		<li><a href="/comm/listComm">나만의 나들이</a></li>
		<li><a href="/planner/getUserPlannerList">나들이 플래너 ▼</a>

			<ul>
				<li><a href="/planner/getUserPlannerList">플래너 게시판</a></li>
				<li><a href="/planner/addPlannerView">플래너 작성</a></li>
				<li><a href="/planner/getMyPlannerList">나만의 플래너</a></li>
				<!-- 
							<ul>
								<li><a href="#">Lorem ipsum dolor</a></li>
								<li><a href="#">Phasellus consequat</a></li>
								<li><a href="#">Magna phasellus</a></li>
								<li><a href="#">Etiam dolore nisl</a></li>
							</ul>
							 --></li>
		<!-- 					<li><a href="#">Veroeros feugiat</a></li> -->
	</ul>
	</li>
	<c:if test="${ empty loginUser }">
		<li><a href="/ticket/listTicket">나들이 티켓</a> <!-- <ul>
			<li><a href="/user/login" data-toggle="tooltip" data-placement="bottom" title="클릭시 로그인 화면으로 이동">로그인 후 나들이 티켓의 추가기능을 사용하실 수 있습니다.</a></li>
		</ul>	 --></li>
	</c:if>
	<c:if test="${ ! empty loginUser }">
		<li><a href="/ticket/listTicket">나들이 티켓 ▼</a>
			<ul>
				<li><a href="/purchase/listBasket">장바구니</a></li>
				<li><a href="/purchase/listPurchase">구매한 티켓</a></li>
				<li><a href="/purchase/listPurchased">지난 티켓 내역</a></li>
				<li><a href="/ticket/getMyTicket">내 티켓 보여주기</a></li>
			</ul>
	</c:if>
	</li>
	<li><a href="/group/listGroup">나들이 모임</a></li>
	<!-- 	
				<li><a href="left-sidebar.html">Left Sidebar</a></li>
				<li><a href="right-sidebar.html">Right Sidebar</a></li>
				<li><a href="no-sidebar.html">No Sidebar</a></li>
		 		-->
	</ul>
</nav>
