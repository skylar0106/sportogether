<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>RivalMatching</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function rivalMatching() {
	if (form.teamName.value == "") {
		alert("TeamName을 입력하십시오.");
		form.teamName.focus();
		return false;
	} 	
	form.submit();
}
function totalLanking(targetUri) {
	form.action = targetUri;
	form.method="GET";		
	form.submit();
}
</script>
</head>
<body>
<br>
<!-- login form  -->
<form name="form" method="POST" action="<c:url value='/team/search' />">
  <table style="width:100%">
	<tr>
	  <td width="20"></td>
	  <td>
	  	<b>UserMan3</b><br><br>
	   	<table>
	   	  <tr>
		    <td class="title">&nbsp;&nbsp;Rival매칭 - TestPage&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
		
	    <!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${loginFailed}">
	  	  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	    </c:if>
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">TeamName</td>
			<td width="250" bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="teamName">
			</td>
		  </tr>
	    </table>
	    <br>	  
	    <table style="width:100%">
		  <tr>
			<td align=left>
			<input type="button" value="라이벌 찾기" onClick="rivalMatching()"> &nbsp;
			<input type="button" value="전체랭킹" onClick="totalLanking(
								'<c:url value='/team/lankingList'/>')"> &nbsp;
			</td>						
		  </tr>
	    </table>
	  </td>	  
	</tr>
  </table>  
</form>
</body>
</html>