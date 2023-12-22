<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/bannerSubTitle.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/totalLanking.css' />" type="text/css">
<head>
  <title>search_team</title>
<script>
		function menu_choice(num){
			switch(num){
			case 1:parent.rigth.documnet.location.replace("/user/myTeamLanking'");break;
			case 1:parent.rigth.documnet.location.replace("/team/portfolio.jsp");break;
			case 1:parent.rigth.documnet.location.replace("/user/portfolio.jsp");break;
			case 1:parent.rigth.documnet.location.replace("/user/update");break;
			}
		}
		function userUpdate(targetUri){
			form.action = targetUri;	
			form.submit();
		}
	</script>
</head>
<body>
  <img class="banner" src="<c:url value='/images/banner.png' />" alt="banner"/>
  <p class ='subTitle'>마이페이지</p>
  <hr>
  <form name="form" method="GET"  >
  <div class = "d1">
		<center><input type = "button" value = "TEAM LANKING" onClick="menu_choice(1)"></center>
	</div>
	<div class = "d2">
		<center><input type = "button" value = "TEAM PORTFOLIO" onClick="menu_choice(2)"></center>
	</div>
	<div class = "d3">
		<center><input type = "button" value = "MY PORTFOLIO" onClick="menu_choice(3)"></center>
	</div>
	<div class = "d4">
		<center><input type = "button" value = "내 정보 수정" onClick="userUpdate(
    		'<c:url value='/user/update'/>')"></center>
	</div>
	</form>
  <div class="mainContent">
	  <div class="myLankingArea">
	  	<p class="myLanking">${lanking.getRanking()}st</p>
	  	<p class="myTeam">${lanking.getTeamName()}</p>
	  	<p class="myTeamScore">${lanking.getMatches()}전 ${lanking.getWin()}승 ${lanking.getLose()}패 ${lanking.getDraw()}무</p>
	  </div>
	  
  </div>
</body>
</html>