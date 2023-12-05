<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	    p.rivalTeam {
	    	margin: 0px; 
	      	margin-left: 15px;
	      	padding: 5px;
	      	display:inline-block;
	      	color: #000000;
	      	background-color: #DDDDDD;
	      	font-size: 20px;
	      	font-weight: bold ;
			font-family: inherit;
			border-radius : 10px;
	    }
	    span.winning {
	    	font-size: 15px;
	    	color: #AFAFAF;
	    	font-weight: bold;
	    	font-family: inherit;
	    }
	    div.rivalSearchArea {
			display: flex;
			justify-content: space-between;
	    }
	    div.rivalArea {
	    	display:inline-block;
	    	align-items: flex-end;
	    }
	    div.searchArea {
	    	display: flex;
	    	align-items: flex-end;
	    	padding-right: 10px;
	    }
	    div.teamList {
	    	display:flex;
	    	overflow-y: scroll;
	    	/*background-color: green;*/
	    	margin: 0 auto;
	    }
	    div.appliBtnArea {
	    	display:inline-block;
	    	justify-content: flex-end;
	    	float: right;
	    	margin-right: 50px;
	    	/*background-color: green;*/
	    }
	    input[name="searchBtn"] {
	    	background-color: #B3B3B3;
	    	color: #FFFFFF;
	    	border: none;
	    	border-radius : 5px;
	    }
	    input[name="fightBtn"] {
	    	background-color: #8AD6D9;
	    	color: #7D7C7C;
	    	border: none;
	    	border-radius : 5px;
	    }
	    input[name="joinBtn"] {
	    	background-color: #8AC6FE;
	    	color: #7D7C7C;
	    	border: none;
	    	border-radius : 5px;
	    }
	    .teamList ul {
      		list-style-type: none;
      		/*background-color: blue;*/
      		width: 100%
      		
    	}
    	.teamList li {
    		padding: 5px;
    		display: inline-block;
      		/*background-color: red;*/
    	}
	    hr {
	    	background-color: black; /*색 안바뀜*/
	    }
	</style>
  <title>search_team</title>
</head>
<body>
  <img src="<c:url value='/images/banner.png' />" width="100%" height="150px" alt="banner"/>
  <p class ='subTitle'>팀 찾기</p>
  <hr>
  <div class='rivalSearchArea'>
	  <div class='rivalArea'>
	  <p class='text1'>라이벌 팀을 추천해드려요</p>
	  <p class='rivalTeam'>&nbsp;${rival}&nbsp;<span class = 'winning'>[승률58.3%]</span>&nbsp;</p>
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