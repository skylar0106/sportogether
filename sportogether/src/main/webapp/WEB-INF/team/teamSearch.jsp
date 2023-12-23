<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "controller.user.UserSessionUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	#info{
	width : 150px;
	height : 32px;
	border: 2px solid #8AD6D9;
	margin-left : 10px;
	border-radius : 12px;
	background-color : white;
	}

</style>
<link rel=stylesheet href="<c:url value='/css/bannerSubTitle.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/searchTeam.css' />"
	type="text/css">
</head>
<body>
<img class="banner" src="<c:url value='/images/banner.png' />"
		alt="banner" />
	<p class='subTitle'></p>
	<hr>
	<div class='rivalSearchArea'>
		<div class='rivalArea'>
			<p class='text1'>���</p>
			
			<p class='rivalTeam'>
				&nbsp;&nbsp;�˻��� : <span style = "font-size:20px;">${searchTeamName}</span>&nbsp;
			</p>
		</div>
	</div>
	<hr>
	<div class='teamList'>
		<ul>
		<c:choose>
		<c:when test = "${not empty searchTeamList}">
			<c:forEach items="${searchTeamList}" var="t"  varStatus = "i">
				<li>${i.index + 1}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${t.name}
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type = "button" id = "info">LEADER [${t.spoLeader}]</button>
				&nbsp;&nbsp;&nbsp;&nbsp; <button type = "button" id = "info">LEVEL.${t.level}</button>
				</li>
				<hr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<li>�˻� ����� �����ϴ�.</li>
			</c:otherwise>
			</c:choose>
		</ul>
	</div>
		<p class = "text1" ><a href = "<c:url value='/team/search' />" style = "text-decoration-line: none;">�ڷ� ����</a></p>
		
				
</body>
</html>