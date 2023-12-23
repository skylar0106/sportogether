<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page import="model.service.dto.*" %>
<%
	Team team = (Team)request.getAttribute("team");
%>
<html>
<head>
<meta charset="UTF-8">

	<script>
	function save(){
		alert("저장이 완료 되었습니다.");
		form.submit();
	}
	</script>
	
    <style>
        .form_radio_btn {
         width: 74px;
         height : 23px;
         border: 1px solid #EAE7E7;
          border-radius: 10px;
        }
       
        .form_radio_btn input[type=radio] {
        display: none;
        }
       
        .form_radio_btn label {
        display: block;
        border-radius: 10px;
        margin: 0 auto;
        text-align: center;
        height: -webkit-fill-available;
        line-height: 25px;
        }
 
        /* Checked */
        .form_radio_btn input[type=radio]:checked + label {
        background: #8AD6D9;
        color: #fff;
        }
        
        /* Disabled */
        .form_radio_btn input[type=radio] + label {
        background: #F9FAFC;
        color: #666;
    }

    .button{
        width: 166px; height: 45px;
        position: absolute;
        background: #8AD6D9;
        border-radius: 10px;
        text-align: center;
        color: white;
        font-size: 30px;
        font-family: Inter;
        font-weight: 700;
        word-wrap: break-word;
        border: none;
    }
    </style>
    <title>teamModi</title>
</head>
<body>
 		<form name="form" method="POST" action="<c:url value='/team/update' />"> 
        <div style="width: 100%; height: 100%; position: relative; background: white">

            <div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
                <img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="<c:url value='/images/Rectangle20.png' />" />
                <div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
                <div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">TEAM MANAGE</div>
            </div>

            <div style="left: 22px; top: 202px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀 관리 > 팀 등록</div>
      
          
            <input type = "button" style="left: 22px; top: 294px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent " value = "팀 등록" onClick = "location.href = 'teamManage_regi.jsp'">
            <input type = "button" style="left: 22px; top: 370px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent " value = "멤버 관리" onClick = "location.href = 'teamManage_member.jsp'">
            <input type = "button" style="left: 22px; top: 446px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent " value = "경기 결과">
            <input type = "button" style="left: 22px; top: 524px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word; border: 0; background-color: transparent " value = "팀 정보 변경">

           <!--세로&가로선-->
            <div style="width: 533px; height: 0px; left: 212px; top: 801px; position: absolute; transform: rotate(-90deg); transform-origin: 0 0; border: 1px black solid"></div>
            <div style="width: 1260px; height: 0px; left: 9px; top: 243.07px; position: absolute; border: 1px black solid"></div>


			<div style="left: 531px; top: 560px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀 ID</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 550px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "teamId" value = "${team.teamId}" readonly>
            

            <div style="left: 531px; top: 603px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀명</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 600px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "name" value = "${team.name}">
            
            
           <!--  <div style="left: 517px; top: 439px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀로고</div>
            <div style="width: 240px; height: 240px; left: 643px; top: 328px; position: absolute; background: #D9D9D9; border-radius: 9999px"></div>
             -->
            
            <div style="left: 499px; top: 704px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">활동 지역</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 701px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "location" value = "${team.location}">



            <div style="left: 531px; top: 652px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">종목</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 650px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "sport" value = "${team.sport}">           
           
           <!-- 
            <div class = "form_radio_btn" style="left: 570px; top: 652px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                <input id = "radio1" type = "radio" name = "sports" value = "soccer">
     
                <label for = "radio1">축구</label>
            </div>

            <div class = "form_radio_btn basketball" style="left: 653px; top: 652px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                <input id = "radio2" type = "radio" name = "sports" value = "basketball">
                <label for = "radio2">농구</label>
            </div>

            <div class = "form_radio_btn baseball" style="left: 736px; top: 652px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                <input id = "radio3" type = "radio" name = "sports" value = "baseball">
                <label for = "radio3">야구</label>
            </div>

            <div class = "form_radio_btn tennis" style="left: 814px; top: 652px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                <input id = "radio4" type = "radio" name = "sports" value = "tennis">
                <label for = "radio4">테니스</label>
            </div>

            <div class = "form_radio_btn badminton" style="left: 891px; top: 652px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                <input id = "radio5" type = "radio" name = "sports" value = "badminton">
                <label for = "radio5">배드민턴</label>
            </div>
             -->
            
        
            <div style=" left: 1086px; top: 764px; position: absolute">
                <input type = "submit" value = "저장" class = "button" Onclick = "save()">
            </div>
	
        </div>
    </form>    
</body>
</html>