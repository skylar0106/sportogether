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
  <title>팀 포트폴리오</title>
</head>
<body>
  <div class='portfolio'>
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
  						<td rowspan = '3'>상패 이미지</td>
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