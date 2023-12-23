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
	    th span:nth-child(1){
    		color:black;
    		font-style : italic;
    		font-style : bold;
		}
		
		th span:nth-child(2){
		    color:#7D7C7C;
		    font-style : italic;
    		font-style : bold;
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
  <title>팀 포트폴리오</title>
</head>
<body>
	 <img class="banner" src="<c:url value='/images/Rectangle20.png' />" alt="banner"/>
  <div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
                <img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="<c:url value='/images/Rectangle20.png' />" />
                <div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
                <div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">MY PAGE</div>
            </div>
            <div style="left: 22px; top: 202px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">마이페이지</div>
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
    <input type = "button" style="left: 22px; top: 370px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "TEAM  PORTFOLIO" onClick="teamPortfolio(
    		'<c:url value='/team/portfolio'>
	        		<c:param name='teamId' value='${user.teamId}'/>
	        		</c:url>')">
    <input type = "button" style="left: 22px; top: 446px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "MY PORTFOLIO" onClick="myPortfolio(
    		'<c:url value='/user/portfolio'>
    		</c:url>')">
    <input type = "button" style="left: 22px; top: 522px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "내 정보 수정" onClick="userUpdate(
    		'<c:url value='/user/update'/>')">
	</form>
  <div class='portfolio' style="position: absolute; left: 250px; top: 270px;">
  
  	<table cellpadding = "10px">
  		<tr>
  			<th colspan = '4' align = 'left'>
  				<font size='5px'><span>TEAM  </span><span>${team.getName()}</span></font>
  			</th>
  		</tr>
  		<tr>
  			<td>전적</td>
  			<td colspan = '2'>승률</tc>
  			<td>팀 레벨</td>
  		</tr>
  		<tr>
  			<td rowspan = '2'>
  				<table bgcolor = "#D9D9D9" style="border-radius : 10px;" cellpadding = "5px">
  					<tr>
  						<th align = 'left'>match</th>
  						<td>${teamScore.getMatches()}</td>
  					</tr>
  					<tr>
  						<th align = 'left'>win</th>
  						<td>${teamScore.getWin()}</td>
  					</tr>
  					<tr>
  						<th align = 'left'>lose</th>
  						<td>${teamScore.getLose()}</td>
  					</tr>
  					<tr>
  						<th align = 'left'>draw</th>
  						<td>${teamScore.getDraw()}</td>
  					</tr>
  				</table>
  			</td>
  			<td colspan = '2' rowspan = '2'>
  				<table cellpadding = "5px">
  					<tr>
  						<td colspan = '2'>
  							<table width = "250px" cellspacing="0" style="border-radius : 10px;">
  								<tr>
  									<td width = '${teamScore.getRate()}%' height='35px' bgcolor='#8AC6FE'>   ${teamScore.getRate()}% </td>
  									<td width = '{100 - ${teamScore.getRate()}}%' bgcolor='#D9D9D9'></td>
  								</tr>
  							</table>
  						</td>
  					</tr>
  					<tr>
  						<td>최근 매치 일자</td>
  						<td>팀 멤버 수</td>
  					</tr>
  					<tr>
  						<th align = 'left'>${recentMatchDate}</th>
  						<th align = 'left'>${teamMemberCount}</th>
  					</tr>
  				</table>
  			</td>
  			<td rowspan = '2'>
  				<table bgcolor = "#D9D9D9" style="border-radius : 10px;" cellpadding = "10px">
  					<tr>
  						<th align = 'left'>LV. ${team.getLevel()}</th>
  						<td rowspan = '3'>
  							<%
							    Team team = (Team) request.getAttribute("team"); // 속성으로 전달된 team 객체를 가져옴
							%>
  							<%
			                    if (team.getLevel() == 1) {
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv1.png" alt="Level 1 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 2) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv2.png" alt="Level 2 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 3) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv3.png" alt="Level 3 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 4) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv4.png" alt="Level 4 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 5) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv5.png" alt="Level 5 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 6) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv6.png" alt="Level 6 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 7) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv7.png" alt="Level 7 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 8) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv8.png" alt="Level 8 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 9) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv9.png" alt="Level 9 Image" width="100" height="100"/>
			                <%
			                    } else if (team.getLevel() == 10) {
			                        // Add similar code for other levels
			                %>
			                <img src="${pageContext.request.contextPath}/images/lv10.png" alt="Level 10 Image" width="100" height="100"/>
  							<%} %>
  						</td>
  					</tr>
  					<tr>
  						<td>다음 레벨까지</td>
  					</tr>
  					<tr>
  						<th align = 'left'>남은 이길 수</th>
  					</tr>
  				</table>
  			</td>
  		</tr>
  		<tr></tr>
  		<tr>
  			<td> </td>
  		</tr>
  		<tr>
  			<td colspan = '4'>팀 소개</td>
  		</tr>
  		<tr>
  			<th colspan = '4' align = 'left'><font size='4px'>${teamIntroduction}</font></th>
  		</tr>
  	</table>
  </div>
</body>
</html>