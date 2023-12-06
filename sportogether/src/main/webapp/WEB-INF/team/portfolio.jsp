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
	    h1 {
	    	font-style : italic;
	    }
	    th span:nth-child(1){
    		color:black;
		}
		
		th span:nth-child(2){
		    color:#7D7C7C;
		}
		
		table{
			border : 1px;
			border-color : black;
		}
	</style>
  <title>팀 포트폴리오</title>
</head>
<body>
  <div class='portfolio'>
  	<table border=1>
  		<tr>
  			<th colspan = '4'><span>TEAM</span><span>${team.getName()}</span></th>
  		</tr>
  		<tr>
  			<td>전적</td>
  			<td colspan = '2'>승률</tc>
  			<td>팀 레벨</td>
  		</tr>
  		<tr>
  			<td rowspan = '2'>
  				<table>
  					<tr>
  						<th>match</th>
  						<td>${teamScore.getMatches()}</td>
  					</tr>
  					<tr>
  						<th>win</th>
  						<td>${teamScore.getWin()}</td>
  					</tr>
  					<tr>
  						<th>lose</th>
  						<td>${teamScore.getLose()}</td>
  					</tr>
  					<tr>
  						<th>draw</th>
  						<td>${teamScore.getDraw()}</td>
  					</tr>
  				</table>
  			</td>
  			<td colspan = '2' rowspan = '2'>
  				<table>
  					<tr>
  						<td colspan = '2'>${teamScore.getRate()}%</td>
  					</tr>
  					<tr>
  						<td>최근 매치 일자</td>
  						<td>팀 멤버 수</td>
  					</tr>
  					<tr>
  						<th>${recentMatchDate}</th>
  						<th>${teamMemberCount}</th>
  					</tr>
  				</table>
  			</td>
  			<td rowspan = '2'>
  				<table>
  					<tr>
  						<th>LV. ${team.getLevel()}</th>
  						<td rowspan = '3'>상패 이미지</td>
  					</tr>
  					<tr>
  						<td>다음 레벨까지</td>
  					</tr>
  					<tr>
  						<th>남은 이길 수</th>
  					</tr>
  				</table>
  			</td>
  		</tr>
  		<tr></tr>
  		<tr>
  			<td colspan = '4'>팀 소개</td>
  		</tr>
  		<tr>
  			<th colspan = '4'>${teamIntroduction}</th>
  		</tr>
  	</table>
  </div>
</body>
</html>