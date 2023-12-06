<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.dao.TeamPortfolioDAO" %>
<%@ page import="model.service.dto.*" %>
<%@ page import="java.time.LocalDate" %>

<html lang="ko">
<head>
	<style>
		p.subTitle {
      		margin: 5px; 
     		padding-left: 5px;
      		color: #7D7C7C;
    	}	
	    p.text1 {
	    	margin: 5px; 
	      	padding-left: 15px;
	      	color: #A8A8A8;
	    }
	    hr {
	    	background-color: black; /*색 안바뀜*/
	    }
	     /* 구분선 스타일 */
	    .menu::after {
	      content: '';
	      position: absolute;
	      top: 0;
	      bottom: 0;
	      left: 0;
	      width: 1px; /* 선의 두께 조절 */
	      background-color: #ccc; /* 선의 색상 설정 */
	    }
	
	    /* menu는 왼쪽 10%를 차지하도록 설정 */
	    .menu {
	      flex: 0 0 10%;
	    }
	
	    /* portfolio는 나머지 영역을 차지하도록 설정 */
	    .portfolio {
	      flex: 1;
	      position: relative;
      	  padding: 20px; /* 여백 추가 */
	    }
	    .portfolioHeader {
	      font-size: 24px;
	      font-weight: bold;
	      margin-bottom: 10px;
	    }
	
	    .record {
	      display: flex;
	      justify-content: space-between;
	      margin-bottom: 10px;
	    }
	
	    .recordItem {
	      flex: 1;
	      text-align: center;
	    }
	
	    .teamIntroduction {
	      margin-top: 20px;
	    }
	    .menu a.selected {
	      font-weight: bold;
	    }
	</style>
  <title>team_portfolio</title>
</head>
<body>
  <img src="<c:url value='/images/banner.png' />" width="100%" height="150px" alt="banner"/>
  <p class ='subTitle'>마이페이지</p>
  <hr>
  <div class = "menu">
  	<li><a href="<c:url value='/team/lanking.jsp' />" class="${currentPage == 'lanking' ? 'selected' : ''}">TEAM LANKING</a></li>
    <li><a href="<c:url value='/team/portfolio.jsp' />"class="${currentPage == 'portfolio' ? 'selected' : ''}">TEAM PORTFOLIO</a></li>
    <li><a href="<c:url value='/user/portfolio.jsp' />"class="${currentPage == 'portfolio' ? 'selected' : ''}">MY PORTFOLIO</a></li>
    <li><a href="<c:url value='/user/profile.jsp' />"class="${currentPage == 'profile' ? 'selected' : ''}">내 정보 수정</a></li>
  </div>
  <div class='portfolio'>
	  <div class='teamName'>
	  	<p class='portfolioHeader'>${team.getTName()}</p>
    </div>
    <div class='record'>
      <div class='recordItem' style="width: 20%;">
        <p>전적</p>
        <p>match ${teamScore.getMatches()} win ${teamScore.getWin()} lose ${teamScore.getLose()} draw ${teamScore.getDraw()}</p>
      </div>
      <div class='recordItem' style="width: 40%;">
        <p>승률</p>
        <p>${teamScore.getRate()}%</p>
      </div>
      <div class='recordItem' style="width: 40%;">
        <!-- 최근 경기 일자, 팀 멤버수, 팀 레벨 -->
        <p>최근 경기 일자</p>
        <p>${recentMatchDate}</p>
        <p>팀 멤버수</p>
        <p>${teamMemberCount}</p>
        <p>팀 레벨</p>
        <p>${team.getLevel()}</p>
      </div>
    </div>
    <div class='teamIntroduction'>
      <!-- 팀 소개 멘트 -->
      <p>${team.getComment()}</p>
    </div>
  </div>
</body>
</html>