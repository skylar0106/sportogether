<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<html>
<head>
<title>mainPage</title>
<script>
function rivalMatching(targetUri) {
	form.action = targetUri;
	form.method="POST";		
	form.submit();
}
function totalLanking(targetUri) {
	form.action = targetUri;
	form.method="GET";		
	form.submit();
}
function login(targetUri) {
	form.action = targetUri;
	form.method="GET";		
	form.submit();
}
</script>
<style>
body, html {
    height: 100%;
    margin: 0;
}

#topMenu {
    height: 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 10px;
}

#topMenu .logo {
    max-height: 60%;

}

#topMenu ul {
    display: flex;
    margin: 0; /* ul의 기본 마진을 제거합니다. */
}

#topMenu ul li {
    list-style: none;
    color: #7D7C7C;
    background-color: white;
    line-height: 30px;
    vertical-align: middle;
    text-align: center;
    margin-right: 10px; /* 각 메뉴 항목 사이의 간격을 조절합니다. */
}

#topMenu .menuLink {
    text-decoration: none;
    color: #7D7C7C;
    display: block;
    width: 150px;
    font-size: 17px;
    font-weight: bold;
    font-family: "Trebuchet MS", Dotum, Arial;
}

#topMenu .menuLink:hover {
    color: white;
    background-color: #8AD6D9;
}

.userActions {
    display: flex;
    align-items: center;
    
}

.join,.sign {
	margin-right: 10px;

}

.mainContent {

    justify-content: center;
    align-items: center;
    

}

.background {
    overflow:auto;
}
.form {
	padding: 0;
	margin: 0;
}


</style>

</head>
<body>
	<nav id="topMenu" >
		<img class="logo" src="<c:url value='/images/logo.png' />" alt="logo"/>
        <ul>
            <li><a class="menuLink" href="#">사이트 소개</a></li>
            <li><a class="menuLink" href="<c:url value='/team/search'>
			 		 </c:url>">팀 찾기</a></li>
            <li><a class="menuLink" href="<c:url value='/team/lankingList'>	   
			 		 </c:url>">전체 랭킹</a></li>
            <li><a class="menuLink" href="#">팀 커뮤니티</a></li>
            <li></li>
        </ul>
        <div class="userActions">
        	<form name="form" method="GET"  action="<c:url value='/user/login' />">
	        	<input class="join" type="button" value="로그인" onClick="login(
	        		'<c:url value='/user/login/form'/>')"> &nbsp;
				<input class="sign" type="button" value="회원가입" onClick=> &nbsp;
        	</form>
        </div>
    </nav>
	<div class="mainContent">
		<img class="background" src="<c:url value='/images/mainBackground2.png' />" alt="background"/>
	
	</div>

</body>
</html>