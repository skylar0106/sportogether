<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.service.dto.*" %>

<%
	User user = (User)request.getAttribute("user");
%>
<html>
<head>
<meta charset="UTF-8">

<script type = "text/javascript">
function save(){
	alert("저장이 완료 되었습니다.");
}

function userModify() {
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	form.submit();
}

function updateSubmit(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>

    <style>
        .form_radio_btn {
         width: 106px;
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
    <title>leaderInfo2</title>
</head>
<body>
        <div style="width: 100%; height: 100%; position: relative; background: white">

            <div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
                <img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="/images/Rectangle20.png" />
                <div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
                <div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">MY PAGE</div>
            </div>

            <div style="left: 22px; top: 202px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">마이페이지</div>
            <div style="left: 251px; top: 294px; position: absolute; color: #7D7C7C; font-size: 24px; font-family: Inter; font-weight: 600; word-wrap: break-word">내 정보 수정</div>
           
          <!--세로선-->
            <div style="width: 533px; height: 0px; left: 212px; top: 801px; position: absolute; transform: rotate(-90deg); transform-origin: 0 0; border: 1px black solid"></div>
          <!--가로선--> 
            <div style="width: 1260px; height: 0px; left: 9px; top: 243.07px; position: absolute; border: 1px black solid"></div>
            
            <form name="form" method="POST" action="<c:url value='/user/update' />">
                <!-- ID -->
                <div style="left: 577px; top: 326px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">회원 ID</div>
                <input type = "text" style="width: 219px; height: 23px; left: 635px; top: 323px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid"  name = "userId" value = "${user.userId}" readonly>
            
                <!-- 이름 -->
                <div style="left: 596px; top: 375px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">이름</div>
                <input type = "text" style = "width: 219px; height: 23px; left: 635px; top: 372px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "name" value = "${user.name}">

                <!--성별 선택-->
                <div style="left: 596px; top: 530px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">성별</div>
                <div>
                    <div class = "form_radio_btn male" style="left: 642px; top: 530px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio1" type = "radio" name = "sex" value = "Male">
                    <label for = "radio1">남자</label>
                </div>

                <div class = "form_radio_btn female" style="left: 752px; top: 530px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio2" type = "radio" name = "sex" value = "Female">
                    <label for = "radio2">여자</label>
                </div>
                </div>

                <!--포지션 선택-->
                <div style="left: 568px; top: 580px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀포지션</div>
                <div>
                    <div class = "form_radio_btn leader" style="left: 642px; top: 580px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio3" type = "radio" name = "position" value = "leader">
                    <label for = "radio3">팀장</label>
                </div>

                <div class = "form_radio_btn member" style="left: 752px; top: 580px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio4" type = "radio" name = "position" value = "member">
                    <label for = "radio4">팀원</label>
                </div>
                </div>
                
                <!-- 닉네임 -->
                <div style="left: 583px; top: 427px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">닉네임</div>
                <input type = "text" style="width: 219px; height: 23px; left: 635px; top: 424px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "nickName" value = "${user.nickName}">

                <!-- 생년월일 -->                
                <div style="left: 568px; top: 479px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">생년월일</div>  
                <input type = "text" style="width: 219px; height: 23px; left: 635px; top: 476px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "birth" value = "${user.birth}">
            
                <!-- 비번 -->
                <div style="left: 568px; top: 632px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">비밀번호</div>
                <input type = "password" style = "width: 219px; height: 23px; left: 635px; top: 629px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "password" value = "${user.password}">
              
                <!-- 비번 확인 -->
                <div style="left: 536px; top: 681px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">비밀번호 확인</div>
                <input type = "password" style="width: 219px; height: 23px; left: 635px; top: 678px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "password2" value = "${user.password}" >
            
            
                <div style="left: 685px; top: 738px; position: absolute">
        		 <input type = "button" value = "저장" class = "button" onClick = "userModify()"/>
            	</div>
            
            </form>
            


            <input type = "button" style="left: 22px; top: 294px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" value = "TEAM RANKING">
            <input type = "button" style="left: 22px; top: 370px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" value = "TEAM  PORTFOLIO">
            <input type = "button" style="left: 22px; top: 446px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 400; word-wrap: break-word; border: 0; background-color: transparent" value = "MY PORTFOLIO">
            <input type = "button" style="left: 22px; top: 522px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word; border: 0; background-color: transparent" value = "내 정보 수정">
 

        </div>
</body>
</html>