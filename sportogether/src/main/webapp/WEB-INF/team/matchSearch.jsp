<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "controller.user.UserSessionUtils" %>

<% 
	boolean hasLogined = UserSessionUtils.hasLogined(session);
%>
<html lang="ko">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/bannerSubTitle.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/searchTeam.css' />"
	type="text/css">
<head>
<style>
#battle_request_form {
	position: absolute;
	display: none;
	border: 1px solid #7d7c7c;
	border-radius: 12px;
	width: 500px;
	top: 50%;
	left: 50%;
	text-align: center;
	background-color: white;
}

#battle_request_form table {
	width: 100%;
	padding: 10%;
	text-align : center;
}

#battle_request_form table td {
	padding: 2%;
}

#close_form {
	display : fixed;
	border: none;
}
</style>
<title>search_team</title>
<script>
	function openRequestForm() {
		document.getElementById('battle_request_form').style.display = 'block';
	}
	function closeForm() {
		document.getElementById('battle_request_form').style.display = 'none';
	}
</script>
</head>
<body>
	<img class="banner" src="<c:url value='/images/banner.png' />"
		alt="banner" />
	<p class='subTitle'>팀 찾기</p>
	<hr>
	<div class='rivalSearchArea'>
		<div class='rivalArea'>
			<p class='text1'>라이벌 팀을 추천해드려요</p>
			<p class='rivalTeam'>
				&nbsp;${rival.getTeamName()}&nbsp;<span class='winning'>[승률${rival.getRate()}%]</span>&nbsp;
			</p>
		</div>
		<!-- 팀 검색 기능 구현,  -->
		<form id = "form" action = "<c:url value='/team/search/result' />">
		<div class='searchArea'>
			<input type="text" name="teamName" />&nbsp;&nbsp; <input
				type="submit" name="searchBtn" value="검색" />
		</div>
		</form>
	</div>
	<hr>
	<div class='teamList'>
		<ul>
			<c:forEach var="team" items="${teamList}" varStatus="status">
				<li>${status.index + 1}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${team.getName()}</li>

				<div class='appliBtnArea'>
					<input type="submit" name="fightBtn" value="대결신청"
						onclick="openRequestForm()" /> <input type="submit" name="joinBtn"
						value="가입신청" />
				</div>
				<hr>
			</c:forEach>
		</ul>
	</div>
	
	<!-- 대결 신청하는 form, 신청하기 버튼 누르면 보이게 -->
	<div id="battle_request_form">
	
		<form method="POST" action="<c:url value='/team/request/create' />">
			<input type = "hidden" name= "rivalId" value = ""/>
			<input type = "hidden" name= "approval" value = "false"/>
			<table>
				<tr>
					<td>종목</td>
					<td><input type="text" name = "sports"/></td>
				</tr>
				<tr>
					<td>메세지</td>
					<td><textarea name = "message" placeholder = "메세지를 입력하세요"></textarea></td>
				</tr>
				<tr>
					<td>시간</td>
					<td><input type="text" name = "date"/></td>
				</tr>
				<tr>
					<td colspan = "2"><input type="submit" value="신청" style = "border :#8AD6D9; border: none;" />&nbsp;<button id="close_form" type="button" onclick="closeForm()">닫기</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>