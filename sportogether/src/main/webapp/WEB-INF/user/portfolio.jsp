<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="model.service.*, model.service.dto.User, model.dao.MyPortfolioDAO" %>
<jsp:useBean id = "usr" class="model.service.dto.User" scope="page"/>
<jsp:setProperty name = "usr" property="*"/>

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
		input[name="save"] {
		 	background-color: #8AD6D9;
		 	color: white;
		 	border: none;
		 	width : 60px;
		 	height : 30px;
		 	border-radius : 5px;
		}
		.check{
		display : inlin-bolck;
		width = 20px; height: 20px;
		border: 1px; 
		border-radius : 3px;
		position: relative;
		cursor: pointer;
		}
		.check:after{
		position: absolute;
		opacity:0;
		transition: 0.2s;
		background-color: #8AD6D9;
		}
		
		#chk:checked + .cricle:after{opacity : 0.5;}
	</style>
  <title>나의 포트폴리오</title>
</head>
<body>
<form method="post" action="/user/portfolio.jsp">
	<div><h2>나의 포트폴리오</h2></div>
	<div class='portfolio'>
  	<table cellpadding = "10px">
  		<tr>
  			<th align = 'left'>
  				<font size='5px'>${user.getName()}</font>
  				<font size='3px'>${user.getSex()}</font>
  			</th>
  		</tr>
  		<tr>
  			<td>경력</td>
  		</tr>
  		<tr>
  			<td><input type = "text" id = "career" size ="100" style=" border-width:0.5px; border-color:#7D7C7C; border-radius: 10px; height:150px; text-align:left; vertical-align:top;" placeholder = "경력사항을 입력하세요"></td>
  		</tr>
  		<tr>
  			<td>관심종목</td>
  		</tr>
  		<tr>
  			<td>
  				<label for = "interst">
	  				<input type = "checkbox" id = "interest"><i class="check"></i><span>축구</span>
	  				<input type = "checkbox" id = "interest"><i class="check"></i><span>농구</span>
	  				<input type = "checkbox" id = "interest"><i class="check"></i><span>야구</span>
	  				<input type = "checkbox" id = "interest"><i class="check"></i><span>테니스</span>
	  				<input type = "checkbox" id = "interest"><i class="check"></i><span>배드민턴</span>
  				</label>
  			</td>
  		</tr>
  		<tr>
  			<td>팀 리더에게</td>
  		</tr>
  		<tr>
  			<td><input type = "text" id = "message" size ="100" style=" border-width:0.5px; border-color:#7D7C7C; border-radius: 10px; height:100px; text-align:left; vertical-align:top;" placeholder = "전하고 싶은 메세지를 입력하세요"></td>
  		</tr>
  		<tr>
  			<td style="text-align : center;"><input type = "submit" name = "save" value = "저장"></td>
  		</tr>
  	</table>
  </div>
</form>
<%
//	MyPortfolioDAO m = new MyPortfolioDAO();
//	int result = m.updateMyPotpolio(usr);
	
//	if(result == 1){
//		System.out.println("추가 완료");
//	}
//	response.sendRedirect("/user/portfolio.jsp");
%> 
</body>
</html>