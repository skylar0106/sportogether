<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <style>
        .button{
            width: 245px;
            height: 100px;
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
    <script>

function main(targetUri) {
	form.action = targetUri;
	form.method="GET";		
	form.submit();
}

</script>
    <title>signUp4</title>
</head>
<body>
        <div style="width: 100%; height: 100%; position: relative; background: white">
            <div style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
                <img style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute" src="<c:url value='/images/Rectangle20.png' />" />
                <div style="left: 14px; top: 16px; position: absolute"><span style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span></div>
                <div style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">JOIN US</div>
            </div>
            <div style="left: 22px; top: 206px; position: absolute"><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">약관동의    >    본인인증    >    </span><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 900; word-wrap: break-word">회원정보입력</span><span style="color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">    >    가입완료</span></div>
            <div style="left: 22px; top: 268px; position: absolute; color: #0F0F0F; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">회원가입 완료</div>
            <div style="left: 449px; top: 427px; position: absolute; color: #3C3C3C; font-size: 40px; font-family: Inter; font-weight: 400; word-wrap: break-word">가입이 완료되었습니다</div>
            <div style="width: 1260px; height: 0px; left: 9px; top: 247px; position: absolute; border: 1px black solid"></div>
           <form name="form" method="GET"  action="<c:url value='/mainPage' />">
            <div style="left: 545px; top: 500px; position: absolute">
                <button class = "button" onClick="main(
	        		'<c:url value='/mainPage'/>')">메인화면</button>
            </div>
            </form>
        </div>
</body>
</html>