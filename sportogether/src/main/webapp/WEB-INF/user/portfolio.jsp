<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.service.*, model.service.dto.User, model.dao.MyPortfolioDAO" %>
<jsp:useBean id = "usr" class="model.service.dto.User" scope="page"/>
<jsp:setProperty name = "usr" property="*"/>

<%
	User user = (User)request.getAttribute("user");
%>

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
	<script>
	function myPortfolioModi(){
		alert("저장이 완료 되었습니다.");
		
		// 선택된 체크박스의 값을 수집
	    var interests = "";
	    var checkboxes = document.getElementsByName('interests');
	    for (var i = 0; i < checkboxes.length; i++) {
	        if (checkboxes[i].checked) {
	            interests += checkboxes[i].value + "/";
	        }
	    }
	    
	    // 구성된 문자열 값을 숨겨진 input에 설정
	    var hiddenInput = document.createElement('input');
	    hiddenInput.type = 'hidden';
	    hiddenInput.name = 'selectedInterests';
	    hiddenInput.value = interests.slice(0, -1); // 마지막의 '\' 제거
	    
	    // 폼에 추가
	    document.form2.appendChild(hiddenInput);
	    
	    document.form2.submit();
	}
	
	document.addEventListener("DOMContentLoaded", function() {
        // 페이지 로딩 시 user.getInterests()에서 받아온 값을 가져와서 배열로 변환
        var interests = "${user.getInterests()}".split("/");
        
        // 각 checkbox에 대해 반복
        var checkboxes = document.getElementsByName('interests');
        for (var i = 0; i < checkboxes.length; i++) {
            // 현재 checkbox의 value가 interests 배열에 포함되어 있다면 체크 표시
            if (interests.includes(checkboxes[i].value)) {
                checkboxes[i].checked = true;
            }
        }
    });
	
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
  <title>나의 포트폴리오</title>
</head>
<body>

	<div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
    	<img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="<c:url value='/images/Rectangle20.png' />" />
    	<div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
    	<div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">MY PAGE</div>
    </div>

    <div style="left: 22px; top: 202px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">마이페이지</div>
    <div style="left: 251px; top: 294px; position: absolute; color: #7D7C7C; font-size: 24px; font-family: Inter; font-weight: 600; word-wrap: break-word">나의 포트폴리오</div>
 
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
    <input type = "button" style="left: 22px; top: 446px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "MY PORTFOLIO" onClick="myPortfolio(
    		'<c:url value='/user/portfolio'>
    		</c:url>')">
    <input type = "button" style="left: 22px; top: 522px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" 
    		value = "내 정보 수정" onClick="userUpdate(
    		'<c:url value='/user/update'/>')">
	</form>

	<div class='portfolio' style="position: absolute; left: 251px; top: 344px;">
	<form name="form2" method="post" action="<c:url value='/user/portfolio' />">
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
  			<td><input type = "text" id = "career" name="career" size ="100" style=" border-width:0.5px; border-color:#7D7C7C; border-radius: 10px; height:150px; text-align:left; vertical-align:top;" placeholder = "경력사항을 입력하세요" value="${user.getCareer()}"></td>
  		</tr>
  		<tr>
  			<td>관심종목</td>
  		</tr>
  		<tr>
  			<td>
  				<label for = "interests">
	  				<input type = "checkbox" id = "interests" name="interests" value = "축구"><i class="check"></i><span>축구</span>
	  				<input type = "checkbox" id = "interests" name="interests" value = "농구"><i class="check"></i><span>농구</span>
	  				<input type = "checkbox" id = "interests" name="interests" value = "야구"><i class="check"></i><span>야구</span>
	  				<input type = "checkbox" id = "interests" name="interests" value = "테니스"><i class="check"></i><span>테니스</span>
	  				<input type = "checkbox" id = "interests" name="interests" value = "배드민턴"><i class="check"></i><span>배드민턴</span>
  				</label>
  			</td>
  		</tr>
  		<tr>
  			<td>팀 리더에게</td>
  		</tr>
  		<tr>
  			<td><input type = "text" id = "tcomment" name="tcomment" size ="100" style=" border-width:0.5px; border-color:#7D7C7C; border-radius: 10px; height:100px; text-align:left; vertical-align:top;" placeholder = "전하고 싶은 메세지를 입력하세요" value="${user.getComment()}"></td>
  		</tr>
  		<tr>
  			<td style="text-align : center;"><input type = "button" name = "save" value = "저장" onClick = "myPortfolioModi()"></td>
  		</tr>
  	</table>
  	</form>
  </div>

</body>
</html>