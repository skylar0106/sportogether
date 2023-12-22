<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/bannerSubTitle.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/totalLanking.css' />" type="text/css">
<head>
  <title>search_team</title>
  <style>
      .button{
        width: 118px; height: 36px;
        background: #8AD6D9;
        border-radius: 10px;
        text-align: center;
        color: white;
        font-size: 20px;
        font-family: Inter;
        font-weight: 700;
        word-wrap: break-word;
        border: none;
      }

    </style>
<script>
		function teamLanking(targetUri){
			form.action = targetUri;
			form.method="POST";		
			form.submit();
		}
		function userUpdate(targetUri){
			form.action = targetUri;	
			form.submit();
		}
		function teamPortfolio(targetUri){
			form.action = targetUri;
			form.method="POST";	
			form.submit();
		}
		function myPortfolio(targetUri){
			form.action = targetUri;
			form.method="POST";	
			form.submit();
		}
	</script>
</head>
<body>
  <img class="banner" src="<c:url value='/images/banner.png' />" alt="banner"/>
  <p class ='subTitle'>마이페이지</p>
  <!--세로선-->
        <div style="width: 533px; height: 0px; left: 212px; top: 801px; position: absolute; transform: rotate(-90deg); transform-origin: 0 0; border: 1px black solid"></div>
      <!--가로선--> 
        <div style="width: 1260px; height: 0px; left: 9px; top: 243.07px; position: absolute; border: 1px black solid"></div>
  <form name="form" method="GET"  >
	
	<input type = "button" style="left: 22px; top: 294px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" 
			value = "TEAM RANKING" onClick="teamLanking(
    		'<c:url value='/user/myTeamLanking'>
    		<c:param name='teamId' value='${user.teamId}'/>
    		</c:url>')">
    <input type = "button" style="left: 22px; top: 370px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "TEAM  PORTFOLIO" onClick="teamPortfolio(
    		'<c:url value='/team/portfolio'>
	        		<c:param name='teamId' value='${user.teamId}'/>
	        		</c:url>')">
    <input type = "button" style="left: 22px; top: 446px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "MY PORTFOLIO" onClick="myPortfolio(
    		'<c:url value='/user/portfolio'>
    		</c:url>')">
    <input type = "button" style="left: 22px; top: 522px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "내 정보 수정" onClick="userUpdate(
    		'<c:url value='/user/update'/>')">
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