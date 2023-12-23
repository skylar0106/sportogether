<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "controller.user.UserSessionUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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
			<p class='text1'>결과</p>
			
			<p class='rivalTeam'>
				&nbsp;&nbsp;검색어 : <span style = "font-size:20px;">${searchTeamName}</span>&nbsp;
			</p>
		</div>
		
	</div>
	<hr>
	<div class='teamList'>
		<ul>
		<c:choose>
		<c:when test = "${not empty searchTeamList}">
			<c:forEach items="${searchTeamList}" var="t"  varStatus = "i">
				<li>${i.index + 1}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${t.name}</li>
				<hr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<li>검색 결과가 없습니다.</li>
			</c:otherwise>
			</c:choose>
		</ul>
	</div>
		<p class = "text1" ><a href = "<c:url value='/team/search' />">뒤로 가기</a></p>

						
				
</body>
</html>