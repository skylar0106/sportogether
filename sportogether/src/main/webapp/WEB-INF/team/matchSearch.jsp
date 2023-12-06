<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/bannerSubTitle.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/searchTeam.css' />" type="text/css">
<head>

  <title>search_team</title>
</head>
<body>
  <img class="banner" src="<c:url value='/images/banner.png' />" alt="banner"/>
  <p class ='subTitle'>팀 찾기</p>
  <hr>
  <div class='rivalSearchArea'>
	  <div class='rivalArea'>
	  <p class='text1'>라이벌 팀을 추천해드려요</p>
	  <p class='rivalTeam'>&nbsp;${rival.getTeamName()}&nbsp;<span class = 'winning'>[승률${rival.getRate()}%]</span>&nbsp;</p>
	  </div>
	  <div class='searchArea'>
	  	<input type="text" name="teamName"  />&nbsp;&nbsp;
	  	<input type="submit" name="searchBtn" value="검색" />
	  </div>
  </div>
  <hr>
  <div class='teamList'>
	  <ul>
	  	<c:forEach var="i" begin="1" end="6">
        	<li>${i}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;내가제일잘나가</li>
        	<div class='appliBtnArea'>
        		<input type="submit" name="fightBtn" value="대결신청" />
        		<input type="submit" name="joinBtn" value="가입신청" />
        	</div>
        	<hr>
      	</c:forEach>
	  </ul>
  </div>
</body>
</html>