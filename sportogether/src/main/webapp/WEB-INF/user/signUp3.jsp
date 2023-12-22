<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
function userCreate(){
	if(form.userId.value == ""){
		alert("사용자 ID를 입력하십시오.")
		//form.userId.focus();
		return false;
	}
	if(form.nickName.value == ""){
		alert("사용자 닉네임을 입력하십시오.")
		//form.nickName.focus();
		return false;
	}
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		//form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		//form.name.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		//form.name.focus();
		return false;
	}
	form.submit();
}

function userList(targetUri){
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

    .button1{
            width: 166px;
            height: 45px;
            position: absolute;
            background: #CED2D2;

            text-align: center;
            color: white;
            font-size: 30px;
            font-family: Inter;
            font-weight: 700;
            word-wrap: break-word;
            border: none;
            border-radius: 10px;
        
        }

        .button2{
            width: 166px;
            height: 45px;
            position: absolute;
            background: #8AD6D9;

            text-align: center;
            color: white;
            font-size: 30px;
            font-family: Inter;
            font-weight: 700;
            word-wrap: break-word;
            border: none;
            border-radius: 10px;
        }
    </style>
    <title>signUp3</title>
</head>
<body>
           <form name = "form" method = "POST" action = "<c:url value = '/user/register'/>">

        <div style="width: 100%; height: 100%; position: relative; background: white">
            <div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
                <img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="<c:url value='/images/Rectangle20.png' />" />
                <div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
                <div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">JOIN US</div>
            </div>
            <div style="left: 22px; top: 206px; position: absolute"><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">약관동의    >    본인인증    >    </span><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word">회원정보입력</span><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">    >    가입완료</span></div>
            
            <div style="left: 22px; top: 268px; position: absolute; color: #0F0F0F; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">정보 입력</div>
            <div style="width: 1260px; height: 0px; left: 9px; top: 247px; position: absolute; border: 1px black solid"></div>


                <div style="left: 505px; top: 334px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">회원 ID</div>
                <input type = "text" style="width: 219px; height: 23px; left: 563px; top: 331px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "userId">
                
                <div style="left: 524px; top: 383px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">이름</div>
                <input type = "text" style="width: 219px; height: 23px; left: 563px; top: 380px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "name">
                
                <div style="left: 511px; top: 435px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">닉네임</div>
                <input type = "text" type = "text" style="width: 219px; height: 23px; left: 563px; top: 432px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "nickName">

                <div style="left: 496px; top: 487px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">생년월일</div>
                <input type = "text" style="width: 219px; height: 23px; left: 563px; top: 484px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "birth">
                
                 
                <div style="left: 524px; top: 539px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">성별</div>
                
                <!--
                <input type = "text" style = "width: 219px; height: 23px; left: 563px; top: 535px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "sex">
                -->
                 
                 <select name = "sex" >
                 	<option value = "Female"></option>
                 	<option value = "Male"></option>
                 </select>
                 
                <!-- 
                <div>
                    <div class = "form_radio_btn male" style="left: 570px; top: 539px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio1" type = "radio" name = "sex" value = "male">
                    <label for = "radio1">남자</label>
                </div>

                <div class = "form_radio_btn female" style="left: 680px; top: 539px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio2" type = "radio" name = "sex" value = "female">
                    <label for = "radio2">여자</label>
                </div>
                </div>
 				-->


                <div style="left: 496px; top: 591px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">팀포지션</div>
                <input type = "text" style = "width: 219px; height: 23px; left: 563px; top: 585px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "position">
				
                <!-- 
                <div>
                    <div class = "form_radio_btn male" style="left: 570px; top: 592px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio3" type = "radio" name = "position" value = "leader">
                    <label for = "radio3">팀장</label>
                </div>

                <div class = "form_radio_btn female" style="left: 680px; top: 592px; position: absolute; color: #7D7C7C; font-size: 12px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                    <input id = "radio4" type = "radio" name = "position" value = "member">
                    <label for = "radio4">팀원</label>
                </div>
                </div>
 				-->
 				
 				

                <div style="left: 496px; top: 640px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">비밀번호</div>
                <input type = "password" style="width: 219px; height: 23px; left: 563px; top: 637px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = "password">
              
                <div style="left: 464px; top: 689px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">비밀번호 확인</div>
                <input type = "password" style="width: 219px; height: 23px; left: 563px; top: 686px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name = password2>

            

            <div style="left: 925px; top: 764px; position: absolute">
                <button class = "button1">이전</button>
            </div>


            <div style="left: 1100px; top: 764px; position: absolute">
                <input type = "button" value = "다음" class = "button2" onClick = "userCreate()">
            </div>

        </div>
        </form>
</body>
</html>