<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <style>
        .button{
            width: 166px;
            height: 45px;
            position: absolute;
            background: #8AD6D9;
            border-radius: 10px;
            
            text-align: center; color: white;
            font-size: 30px;
            font-family: Inter;
            font-weight: 700;
            word-wrap: break-word;
            border: none;
        }
    </style>
    <title>signUp</title>
</head>
<body>
        <div style="width: 100%; height: 100%; position: relative; background: white">

            <div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
                <img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="Rectangle20.png" />
                <div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
                <div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">JOIN US</div>
            </div>

            <div style="left: 22px; top: 206px; position: absolute"><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word">약관동의</span><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">    >    본인인증    >    회원정보입력    >    가입완료</span></div>
            <div style="left: 22px; top: 268px; position: absolute; color: #0F0F0F; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">서비스 이용 약관</div>

            <label style="left: 1120px; top: 641px; position: absolute; color: #0F0F0F; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">
                <input type = "checkbox" name = "agree" value = "yes"/>동의합니다
                	
            </label>
            
            <div style="width: 1260px; height: 0px; left: 9px; top: 247px; position: absolute; border: 1px black solid"></div>
            
           <div style="width: 1233px; height: 316px; left: 22px; top: 309px; position: absolute; background: rgba(217, 217, 217, 0); border: 1px black solid"></div>
   
            <div style="left: 1095px; top: 680px; position: absolute">
                <button class = "button" onClick="location.href = 'signUp2.jsp'">확인</button>
            </div>

        </div>
</body>
</html>