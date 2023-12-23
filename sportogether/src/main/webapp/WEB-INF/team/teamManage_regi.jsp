<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
	<script>
	function teamManage(){
		form.action = targetUri;
		form.method="GET";		// register form 요청
		form.submit();
	}
	function teamCreate(targetUri) {
		alert("등록이 완료 되었습니다.");
		form.action = targetUri;
		form.method="POST";		// register form 요청
		form.submit();
	}
	function teamUpdate(targetUri){
		form.action = targetUri;
		form.method="POST";		// register form 요청
		form.submit();
	}
	</script>
    <style>
        
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
    <title>teamRegi</title>
</head>
<body>
<!-- 
 -->
<form name="form" method="POST" action= "<c:url value='/team/register' />"> 

        <div style="width: 100%; height: 100%; position: relative; background: white">

            <div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
            
                <img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="<c:url value='/images/Rectangle20.png' />" />
               
                <div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
                <div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">TEAM MANAGE</div>
            </div>

            <div style="left: 22px; top: 202px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀 관리 > 팀 등록</div>
      
          
            <input type = "button" style="left: 22px; top: 294px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word; border: 0; background-color: transparent " value = "팀 등록" onClick = "teamManage(
        		'<c:url value='/team/register'/>')">
            <input type = "button" style="left: 22px; top: 370px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent " value = "멤버 관리" onClick = >
            <input type = "button" style="left: 22px; top: 446px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent " value = "경기 결과">
            <input type = "button" style="left: 22px; top: 524px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent " value = "팀 정보 변경" onClick = "teamUpdate(
	        		'<c:url value='/team/register'/>')">

           <!--세로&가로선-->
            <div style="width: 533px; height: 0px; left: 212px; top: 801px; position: absolute; transform: rotate(-90deg); transform-origin: 0 0; border: 1px black solid"></div>
            <div style="width: 1260px; height: 0px; left: 9px; top: 243.07px; position: absolute; border: 1px black solid"></div>

			<!-- 
			<div style="left: 531px; top: 560px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀 ID</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 550px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "teamId">
             -->

            <div style="left: 531px; top: 400px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀명</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 400px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "name">
            
           
            
            <div style="left: 499px; top: 450px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">활동 지역</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 450px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "location">



            <div style="left: 531px; top: 500px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">종목</div>
            <input type = "text" style="width: 406px; height: 23px; left: 570px; top: 500px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "sport">           
            
        
            <div style=" left: 1086px; top: 600px; position: absolute">
                <input type = "submit" value = "등록" class = "button" Onclick = "teamCreate(
	        		'<c:url value='/team/register'/>')">
            </div>
	
        </div>
    </form>    
        
</body>
</html>