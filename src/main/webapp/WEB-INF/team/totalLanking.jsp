<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/bannerSubTitle.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/totalLanking.css' />" type="text/css">
<head>
  <title>search_team</title>
  <style>
  </style>
</head>
<body>
  <img class="banner" src="<c:url value='/images/banner.png' />" alt="banner"/>
  <p class ='subTitle'>전체 랭킹</p>
  <hr>
  <div class="mainContent">
	  <div class="myLankingArea">
	  	<p class="myLanking">${lanking.getRanking()}st</p>
	  	<p class="myTeam">${lanking.getTeamName()}</p>
	  	<p class="myTeamScore">${lanking.getMatches()}전 ${lanking.getWin()}승 ${lanking.getLose()}패 ${lanking.getDraw()}무</p>
	  </div>
	  <div class="lankingListArea">
	  	<ul>
		  	<c:forEach var="lanking" items="${lankingList}">
	        	<li>
	        		<div class="rank">${lanking.getRanking()}st</div>
	        		<div class="teamName">${lanking.getTeamName()}</div>
	        		<div class="score">${lanking.getMatches()}전 ${lanking.getWin()}승 ${lanking.getLose()}패 ${lanking.getDraw()}무</div>
	        	</li>

	        	<hr>
	      	</c:forEach>
		  </ul>
	  </div>
  </div>
</body>
</html>